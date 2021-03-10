package dev.b3nedikt.reword.transformer

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.nhaarman.mockitokotlin2.whenever
import dev.b3nedikt.reword.TestApplication
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], application = TestApplication::class)
class CollapsingToolbarLayoutTransformerTest {

    private var transformer = CollapsingToolbarLayoutViewTransformer

    private val context: Context
        get() {
            val context = Mockito.spy(ApplicationProvider.getApplicationContext() as Context)
            val resources = Mockito.spy(context.resources)

            whenever(context.resources).thenReturn(resources)
            Mockito.doReturn(TITLE_ATTR_VALUE).whenever(resources).getText(TITLE_RES_ID)

            return context
        }

    @Test
    fun shouldTransformCollapsingToolbarLayout() {

        val view = CollapsingToolbarLayout(context)
        transformer.apply {
            view.transform(getAttributeSet(false))
        }

        view.title shouldBeEqualTo  TITLE_ATTR_VALUE

        transformer.apply {
            view.transform(getAttributeSet(true))
        }

        view.title shouldBeEqualTo  TITLE_ATTR_VALUE
    }

    private fun getAttributeSet(withAndroidPrefix: Boolean) = mapOf(
            getAttributeKey(withAndroidPrefix) to TITLE_RES_ID,
    )

    private fun getAttributeKey(withAndroidPrefix: Boolean) =
            (if (withAndroidPrefix) "android:" else "") + TITLE_ATTR_KEY

    private companion object {
        private const val TITLE_RES_ID = 0x7f0f0123
        private const val TITLE_ATTR_KEY = "title"
        private const val TITLE_ATTR_VALUE = "TEXT_ATTR_VALUE"
    }
}