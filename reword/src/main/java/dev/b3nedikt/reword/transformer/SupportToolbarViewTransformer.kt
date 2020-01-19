package dev.b3nedikt.reword.transformer

import androidx.appcompat.widget.Toolbar

/**
 * A [ViewTransformer] which transforms the androidX Toolbar
 */
internal object SupportToolbarViewTransformer : AbstractViewTransformer<Toolbar>() {

    private const val ATTRIBUTE_TITLE = "title"
    private const val ATTRIBUTE_SUBTITLE = "subtitle"
    private const val ATTRIBUTE_APP_TITLE = "app:title"
    private const val ATTRIBUTE_APP_SUBTITLE = "app:subtitle"

    override val viewType = Toolbar::class.java

    override val supportedAttributes = setOf(ATTRIBUTE_TITLE, ATTRIBUTE_SUBTITLE,
            ATTRIBUTE_APP_TITLE, ATTRIBUTE_APP_SUBTITLE)

    override fun Toolbar.transform(attrs: Map<String, Int>) {
        attrs.forEach { attribute ->
            when (attribute.key) {
                ATTRIBUTE_TITLE, ATTRIBUTE_APP_TITLE ->
                    updateTexts(attribute.value, this::setTitle)
                ATTRIBUTE_SUBTITLE, ATTRIBUTE_APP_SUBTITLE ->
                    updateTexts(attribute.value, this::setSubtitle)
            }
        }
    }
}