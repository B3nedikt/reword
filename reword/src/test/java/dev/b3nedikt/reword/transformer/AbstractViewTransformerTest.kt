package dev.b3nedikt.reword.transformer

import android.os.Build
import android.util.AttributeSet
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class AbstractViewTransformerTest {

    private fun getAttributeSet(withAppPrefix: Boolean): AttributeSet = mock {
        on { attributeCount } doReturn TITLE_ATTR_INDEX + 2
        on { getAttributeName(anyInt()) } doReturn "other_attribute"
        on { getAttributeName(TITLE_ATTR_INDEX) } doReturn getAttributeKey(withAppPrefix)
        on { getAttributeValue(TITLE_ATTR_INDEX) } doReturn "@${TITLE_RES_ID}"
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