package dev.b3nedikt.reword.transformer

import android.content.Context
import android.content.res.Resources
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.google.android.material.textfield.TextInputLayout
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import dev.b3nedikt.reword.TestApplication
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], application = TestApplication::class)
class TextInputLayoutViewTransformerTest {

    private val transformer = TextInputLayoutViewTransformer

    private val context: Context
        get() {
            val context = spy(ApplicationProvider.getApplicationContext() as Context)
            val resources = spy<Resources>(context.resources)

            whenever(context.resources) doReturn resources

            doReturn(HINT_ATTR_VALUE).whenever(resources).getText(HINT_RES_ID)

            return context
        }

    @Test
    fun shouldTransformTextInputLayout() {
        val view = TextInputLayout(context)

        transformer.apply {
            view.transform(getAttributeSet(false))
        }

        view.hint shouldBeEqualTo HINT_ATTR_VALUE
    }

    @Test
    fun shouldTransformTextInputLayout_withAndroidNamespace() {
        val view = TextInputLayout(context)

        transformer.apply {
            view.transform(getAttributeSet(true))
        }

        view.hint shouldBeEqualTo HINT_ATTR_VALUE
    }

    private fun getAttributeSet(withAndroidNamespace: Boolean) = mapOf(
            getAttributeKey(withAndroidNamespace) to HINT_RES_ID
    )

    private fun getAttributeKey(withAndroidNamespace: Boolean) =
            (if (withAndroidNamespace) "android:" else "") + HINT_ATTR_KEY

    private companion object {
        private const val HINT_ATTR_KEY = "hint"
        private const val HINT_RES_ID = 0x7f0f0123
        private const val HINT_ATTR_VALUE = "HINT_ATTR_VALUE"
    }
}