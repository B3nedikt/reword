package dev.b3nedikt.reword.transformer

import android.content.Context
import android.os.Build
import android.widget.EditText
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class TextViewViewTransformerTest {

    private var transformer = TextViewViewTransformer

    private val context: Context
        get() {
            val context = spy(ApplicationProvider.getApplicationContext() as Context)
            val resources = spy(context.resources)

            whenever(context.resources).thenReturn(resources)
            doReturn(TEXT_ATTR_VALUE).whenever(resources).getText(TEXT_RES_ID)
            doReturn(HINT_ATTR_VALUE).whenever(resources).getText(HINT_RES_ID)

            return context
        }

    @Test
    fun shouldTransformTextView() {

        val view = TextView(context)
        transformer.apply {
            view.transform(getAttributeSet(false))
        }

        view.text shouldBeEqualTo  TEXT_ATTR_VALUE
        view.hint shouldBeEqualTo  HINT_ATTR_VALUE

        transformer.apply {
            view.transform(getAttributeSet(true))
        }

        view.text shouldBeEqualTo  TEXT_ATTR_VALUE
        view.hint shouldBeEqualTo  HINT_ATTR_VALUE
    }

    @Test
    fun shouldTransformExtendedViews() {

        val view = EditText(context)
        transformer.apply {
            view.transform(getAttributeSet(false))
        }

        view.text.toString() shouldBeEqualTo TEXT_ATTR_VALUE
        view.hint shouldBeEqualTo HINT_ATTR_VALUE

        transformer.apply {
            view.transform(getAttributeSet(true))
        }

        view.text.toString() shouldBeEqualTo TEXT_ATTR_VALUE
        view.hint shouldBeEqualTo HINT_ATTR_VALUE
    }

    private fun getAttributeSet(withAndroidPrefix: Boolean) = mapOf(
            getAttributeKey(withAndroidPrefix, TEXT_ATTR_KEY) to TEXT_RES_ID,
            getAttributeKey(withAndroidPrefix, HINT_ATTR_KEY) to HINT_RES_ID
    )

    private fun getAttributeKey(withAndroidPrefix: Boolean, key: String) =
            (if (withAndroidPrefix) "android:" else "") + key

    private companion object {
        private const val TEXT_RES_ID = 0x7f0f0123
        private const val TEXT_ATTR_KEY = "text"
        private const val TEXT_ATTR_VALUE = "TEXT_ATTR_VALUE"

        private const val HINT_RES_ID = 0x7f0f0124
        private const val HINT_ATTR_KEY = "hint"
        private const val HINT_ATTR_VALUE = "HINT_ATTR_VALUE"
    }
}