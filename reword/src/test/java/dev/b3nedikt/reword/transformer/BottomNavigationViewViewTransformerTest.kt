package dev.b3nedikt.reword.transformer

import android.content.Context
import android.content.res.Resources
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.b3nedikt.reword.TestApplication
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.spy
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], application = TestApplication::class)
class BottomNavigationViewViewTransformerTest {

    private var transformer = BottomNavigationViewViewTransformer

    private val context: Context
        get() {
            val context = spy(ApplicationProvider.getApplicationContext() as Context)
            val resources = spy<Resources>(context.resources)

            whenever(context.resources) doReturn resources

            doReturn(TITLE_ATTR_VALUE).whenever(resources).getString(TITLE_RES_ID)
            doReturn(MENU_ATTR_VALUE).whenever(resources).getString(MENU_RES_ID)

            return context
        }

    @Ignore("Better mocking needed for this test to work!")
    @Test
    fun shouldTransformToolbar() {
        val view = BottomNavigationView(context)
        view.menu.add(TITLE_RES_ID)

        transformer.apply {
            view.transform(getAttributeSet(false))
        }

        view.menu.getItem(0).title shouldBeEqualTo TITLE_ATTR_VALUE
    }

    @Ignore("Better mocking needed for this test to work!")
    @Test
    fun shouldTransformToolbar_withAppPrefix() {
        val view = BottomNavigationView(context)
        view.menu.add(TITLE_RES_ID)

        transformer.apply {
            view.transform(getAttributeSet(true))
        }

        view.menu.getItem(0).title shouldBeEqualTo TITLE_ATTR_VALUE
    }

    private fun getAttributeSet(withAndroidPrefix: Boolean) = mapOf(
            getAttributeKey(withAndroidPrefix) to TITLE_RES_ID,
            MENU_ATTR_KEY to MENU_RES_ID
    )

    private fun getAttributeKey(withAndroidPrefix: Boolean) =
            (if (withAndroidPrefix) "android:" else "") + TITLE_ATTR_KEY

    private companion object {
        private const val TITLE_ATTR_KEY = "title"
        private const val TITLE_RES_ID = 0x7f0f0123
        private const val TITLE_ATTR_VALUE = "TITLE_ATTR_VALUE"

        private const val MENU_ATTR_KEY = "menu"
        private const val MENU_RES_ID = 0x7f0f0124
        private const val MENU_ATTR_VALUE = "menu"
    }
}