package dev.b3nedikt.reword.util

import android.os.Build
import android.util.AttributeSet
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class AttributeSetExtensionsTest {

    @Test
    fun extractAttributesShouldGetTheCorrectAttributesFromTheAttributeSet() {
        getAttributeSet()
                .extractAttributes(setOf(TITLE_ATTR_KEY))
                .shouldBeEqualTo(mapOf(TITLE_ATTR_KEY to TITLE_RES_ID))
    }

    private fun getAttributeSet(): AttributeSet = mock {
        on { attributeCount } doReturn TITLE_ATTR_INDEX + 2
        on { getAttributeName(anyInt()) } doReturn "other_attribute"
        on { getAttributeName(TITLE_ATTR_INDEX) } doReturn TITLE_ATTR_KEY
        on { getAttributeValue(TITLE_ATTR_INDEX) } doReturn "@$TITLE_RES_ID"
        on { getAttributeResourceValue(eq(TITLE_ATTR_INDEX), anyInt()) } doReturn TITLE_RES_ID
    }

    private companion object {
        private const val TITLE_ATTR_INDEX = 3
        private const val TITLE_RES_ID = 0x7f0f0123
        private const val TITLE_ATTR_KEY = "title"
    }
}