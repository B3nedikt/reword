package dev.b3nedikt.reword.transformer

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.eq
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class SupportToolbarViewTransformerTest {

    private val transformer = SupportToolbarViewTransformer

    private val context: Context
        get() {
            val context = spy(ApplicationProvider.getApplicationContext() as Context)
            val resources = spy<Resources>(context.resources)

            whenever(context.resources) doReturn resources

            doReturn(TITLE_ATTR_VALUE).whenever(resources).getString(TITLE_RES_ID)

            return context
        }

    @Test
    fun shouldTransformToolbar() {
        val view = Toolbar(context)

        transformer.apply {
            view.transform(transformer.extractAttributes(view, getAttributeSet(false)))
        }

        view.title shouldBeEqualTo TITLE_ATTR_VALUE
    }

    @Test
    fun shouldTransformToolbar_withAppPrefix() {
        val view = Toolbar(context)

        transformer.apply {
            view.transform(transformer.extractAttributes(view, getAttributeSet(true)))
        }

        view.title shouldBeEqualTo TITLE_ATTR_VALUE
    }

    private fun getAttributeSet(withAppPrefix: Boolean): AttributeSet = mock {
        on { attributeCount } doReturn TITLE_ATTR_INDEX + 2
        on { getAttributeName(anyInt()) } doReturn "other_attribute"
        on { getAttributeName(TITLE_ATTR_INDEX) } doReturn getAttributeKey(withAppPrefix)
        on { getAttributeValue(TITLE_ATTR_INDEX) } doReturn "@$TITLE_RES_ID"
        on { getAttributeResourceValue(eq(TITLE_ATTR_INDEX), anyInt()) } doReturn TITLE_RES_ID
    }

    private fun getAttributeKey(withAppPrefix: Boolean) =
            (if (withAppPrefix) "app:" else "") + TITLE_ATTR_KEY

    private companion object {
        private const val TITLE_ATTR_INDEX = 3
        private const val TITLE_RES_ID = 0x7f0f0123
        private const val TITLE_ATTR_KEY = "title"
        private const val TITLE_ATTR_VALUE = "TITLE_ATTR_VALUE"
    }
}
