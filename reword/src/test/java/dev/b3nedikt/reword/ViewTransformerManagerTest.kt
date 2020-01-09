package dev.b3nedikt.reword

import android.os.Build
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import dev.b3nedikt.reword.transformer.ViewTransformer
import org.amshove.kluent.shouldBe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ViewTransformerManagerTest {

    private lateinit var transformerManager: ViewTransformerManager

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

        textView shouldBe  transformedView
    }
}