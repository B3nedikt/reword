package dev.b3nedikt.reword.creator

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabItem
import dev.b3nedikt.reword.R
import dev.b3nedikt.reword.util.extractAttributes
import dev.b3nedikt.reword.views.RewordTabItem

/**
 * [ViewCreator] which creates [RewordTabItem]s.
 */
internal object TabItemViewCreator : ViewCreator<TabItem> {

    private const val ATTRIBUTE_TEXT = "text"
    private const val ATTRIBUTE_ANDROID_TEXT = "android:text"

    private val supportedAttributes = setOf(ATTRIBUTE_TEXT, ATTRIBUTE_ANDROID_TEXT)

    override val viewName = TabItem::class.qualifiedName.toString()

    override fun createView(context: Context, attrs: AttributeSet?): TabItem {

        val extractedAttributes = attrs?.extractAttributes(supportedAttributes)
        val textResourceId = extractedAttributes?.toList()?.firstOrNull()?.second

        return RewordTabItem(context, attrs, textResourceId).apply {
            setTag(R.id.view_tag, extractedAttributes)
        }
    }
}