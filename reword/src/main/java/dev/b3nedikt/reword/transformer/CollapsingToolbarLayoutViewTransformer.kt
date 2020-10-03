package dev.b3nedikt.reword.transformer

import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * A transformer which transforms the title of a [CollapsingToolbarLayout]
 */
internal object CollapsingToolbarLayoutViewTransformer : AbstractViewTransformer<CollapsingToolbarLayout>() {

    private const val ATTRIBUTE_TITLE = "title"
    private const val ATTRIBUTE_ANDROID_TITLE = "android:title"

    override val viewType = CollapsingToolbarLayout::class.java

    override val supportedAttributes = setOf(ATTRIBUTE_TITLE, ATTRIBUTE_ANDROID_TITLE)

    override fun CollapsingToolbarLayout.transform(attrs: Map<String, Int>) {
        attrs.forEach { entry ->
            when (entry.key) {
                ATTRIBUTE_ANDROID_TITLE, ATTRIBUTE_TITLE -> updateTexts(entry.value, this::setTitle)
            }
        }
    }
}