package dev.b3nedikt.reword

import android.os.Build
import android.widget.EditText
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import dev.b3nedikt.reword.transformer.ViewTransformer
import org.amshove.kluent.shouldBe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ViewTransformerManagerTest {

    private lateinit var transformerManager: ViewTransformerManager

    private val transformerTextView = object : ViewTransformer<TextView> {
        override val viewType = TextView::class.java
        override val supportedAttributes = emptySet<String>()
        override fun TextView.transform(attrs: Map<String, Int>) = run { id = TEXT_VIEW_ID }
    }
    private val transformerEditText = object : ViewTransformer<EditText> {
        override val viewType = EditText::class.java
        override val supportedAttributes = emptySet<String>()
        override fun EditText.transform(attrs: Map<String, Int>) = run { id = EDIT_TEXT_ID }
    }

    @Before
    fun setUp() {
        transformerManager = ViewTransformerManager()
    }

    @Test
    fun shouldTransformView() {
        val textView = TextView(ApplicationProvider.getApplicationContext())

        val transformer = mock<ViewTransformer<TextView>> {
            on { viewType } doReturn TextView::class.java
        }

        transformerManager.registerTransformer(transformer)

        val transformedView = transformerManager.transform(textView, mock())

        textView shouldBe transformedView
    }

    @Test
    fun shouldApplyCurrentTransformer() {
        val editText = EditText(ApplicationProvider.getApplicationContext())

        transformerManager.registerTransformer(transformerTextView)
        transformerManager.registerTransformer(transformerEditText)

        val transformedView = transformerManager.transform(editText, mock())

        transformedView.id shouldBe EDIT_TEXT_ID
    }

    @Test
    fun shouldApplyParentTransformer() {
        val editText = EditText(ApplicationProvider.getApplicationContext())

        transformerManager.registerTransformer(transformerTextView)

        val transformedView = transformerManager.transform(editText, mock())

        transformedView.id shouldBe TEXT_VIEW_ID
    }

    companion object {
        const val TEXT_VIEW_ID = 1
        const val EDIT_TEXT_ID = 2
    }
}