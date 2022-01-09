package dev.b3nedikt.reword.transformer

import android.content.Context
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.widget.Toolbar
import androidx.test.core.app.ApplicationProvider
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.spy
import org.mockito.kotlin.whenever
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class AppCompatToolbarViewTransformerTest {

    private val transformer = AppCompatToolbarViewTransformer

    private val context: Context
        get() {
            val context = spy(ApplicationProvider.getApplicationContext() as Context)
            val resources = spy<Resources>(context.resources)

            whenever(context.resources) doReturn resources

            doReturn(TITLE_ATTR_VALUE).whenever(resources).getText(TITLE_RES_ID)

            return context
        }

    @Test
    fun shouldTransformToolbar() {
        val view = Toolbar(context)

        transformer.apply {
            view.transform(getAttributeSet(false))
        }

        view.title shouldBeEqualTo TITLE_ATTR_VALUE
    }

    @Test
    fun shouldTransformToolbar_withAppPrefix() {
        val view = Toolbar(context)

        transformer.apply {
            view.transform(getAttributeSet(true))
        }

        view.title shouldBeEqualTo TITLE_ATTR_VALUE
    }

    private fun getAttributeSet(withAppPrefix: Boolean) = mapOf(
            getAttributeKey(withAppPrefix) to TITLE_RES_ID
    )

    private fun getAttributeKey(withAppPrefix: Boolean) =
            (if (withAppPrefix) "app:" else "") + TITLE_ATTR_KEY

    private companion object {
        private const val TITLE_ATTR_KEY = "title"
        private const val TITLE_RES_ID = 0x7f0f0123
        private const val TITLE_ATTR_VALUE = "TITLE_ATTR_VALUE"
    }
}
