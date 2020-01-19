package dev.b3nedikt.reword.transformer

import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class AbstractViewTransformerTest {

    @Test
    fun extractAttributesShouldGetTheCorrectAttributesFromTheAttributeSet() {
        val view = View(ApplicationProvider.getApplicationContext())

        ConcreteViewTransformer
                .extractAttributes(view, getAttributeSet())[TITLE_ATTR_KEY]
                .shouldBeEqualTo(TITLE_RES_ID)
    }

    private fun getAttributeSet(): AttributeSet = mock {
        on { attributeCount } doReturn TITLE_ATTR_INDEX + 2
        on { getAttributeName(anyInt()) } doReturn "other_attribute"
        on { getAttributeName(TITLE_ATTR_INDEX) } doReturn TITLE_ATTR_KEY
        on { getAttributeValue(TITLE_ATTR_INDEX) } doReturn "@${TITLE_RES_ID}"
        on { getAttributeResourceValue(eq(TITLE_ATTR_INDEX), anyInt()) } doReturn TITLE_RES_ID
    }

    private object ConcreteViewTransformer : AbstractViewTransformer<View>() {

        override val viewType = View::class.java

        override val supportedAttributes: Set<String> = setOf(TITLE_ATTR_KEY)

        override fun View.transform(attrs: Map<String, Int>) = Unit
    }

    private companion object {
        private const val TITLE_ATTR_INDEX = 3
        private const val TITLE_RES_ID = 0x7f0f0123
        private const val TITLE_ATTR_KEY = "title"
    }
}