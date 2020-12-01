package dev.b3nedikt.reword.transformer

import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout

/**
 * A transformer which transforms the text of [TabItem]s
 */
internal object TabViewViewTransformer : AbstractViewTransformer<TabLayout.TabView>() {

    private const val ATTRIBUTE_TEXT = "text"
    private const val ATTRIBUTE_ANDROID_TEXT = "android:text"

    override val viewType = TabLayout.TabView::class.java

    override val supportedAttributes = setOf(ATTRIBUTE_TEXT, ATTRIBUTE_ANDROID_TEXT)

    override fun TabLayout.TabView.transform(attrs: Map<String, Int>) {
        attrs.forEach { entry ->
            when (entry.key) {
                ATTRIBUTE_ANDROID_TEXT, ATTRIBUTE_TEXT -> updateTexts(entry.value) { tab?.text = it }
            }
        }
    }
}