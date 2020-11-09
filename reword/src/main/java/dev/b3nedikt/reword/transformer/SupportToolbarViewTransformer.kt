package dev.b3nedikt.reword.transformer

import androidx.appcompat.widget.Toolbar

/**
 * A [ViewTransformer] which transforms the androidX [Toolbar]
 */
internal object SupportToolbarViewTransformer : AbstractViewTransformer<Toolbar>() {

    private const val ATTRIBUTE_TITLE = "title"
    private const val ATTRIBUTE_SUBTITLE = "subtitle"
    private const val ATTRIBUTE_APP_TITLE = "app:title"
    private const val ATTRIBUTE_APP_SUBTITLE = "app:subtitle"
    private const val ATTRIBUTE_ANDROID_TITLE = "android:title"
    private const val ATTRIBUTE_MENU = "menu"
    private const val ATTRIBUTE_APP_MENU = "app:menu"
    private const val ATTRIBUTE_ID = "id"
    private const val ATTRIBUTE_ANDROID_ID = "android:id"
    private const val ATTRIBUTE_TITLE_CONDENSED = "titleCondensed"
    private const val ATTRIBUTE_ANDROID_TITLE_CONDENSED = "android:titleCondensed"
    private const val XML_MENU = "menu"
    private const val XML_ITEM = "item"

    override val viewType = Toolbar::class.java

    override val supportedAttributes = setOf(
            ATTRIBUTE_MENU, ATTRIBUTE_APP_MENU, ATTRIBUTE_ID,
            ATTRIBUTE_ANDROID_ID, ATTRIBUTE_ANDROID_TITLE, ATTRIBUTE_TITLE_CONDENSED,
            ATTRIBUTE_ANDROID_TITLE_CONDENSED, XML_MENU, XML_ITEM, ATTRIBUTE_TITLE, ATTRIBUTE_SUBTITLE,
            ATTRIBUTE_APP_TITLE, ATTRIBUTE_APP_SUBTITLE)

    override fun Toolbar.transform(attrs: Map<String, Int>) {
        attrs.forEach { attribute ->
            when (attribute.key) {
                ATTRIBUTE_TITLE, ATTRIBUTE_APP_TITLE -> updateTexts(attribute.value, this::setTitle)
                ATTRIBUTE_SUBTITLE, ATTRIBUTE_APP_SUBTITLE -> updateTexts(attribute.value, this::setSubtitle)
                ATTRIBUTE_MENU, ATTRIBUTE_APP_MENU -> {
                    val menuItemsStrings = PopupMenuHelper.getMenuItemsStrings(
                            resources = resources,
                            resId = attrs[attribute.key] ?: 0
                    )

                    menuItemsStrings.forEach {
                        if (it.value.title != 0) {
                            menu.findItem(it.key).title = resources.getString(it.value.title)
                        }
                        if (it.value.titleCondensed != 0) {
                            menu.findItem(it.key).titleCondensed = resources.getString(it.value.titleCondensed)
                        }
                    }
                }
            }
        }
    }
}