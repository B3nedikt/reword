package dev.b3nedikt.reword.transformer

import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * A transformer which transforms BottomNavigationView: it transforms the texts coming from the menu.
 */
internal object BottomNavigationViewViewTransformer : AbstractViewTransformer<BottomNavigationView>() {

    private const val ATTRIBUTE_MENU = "menu"
    private const val ATTRIBUTE_APP_MENU = "app:menu"
    private const val ATTRIBUTE_ID = "id"
    private const val ATTRIBUTE_ANDROID_ID = "android:id"
    private const val ATTRIBUTE_TITLE = "title"
    private const val ATTRIBUTE_ANDROID_TITLE = "android:title"
    private const val ATTRIBUTE_TITLE_CONDENSED = "titleCondensed"
    private const val ATTRIBUTE_ANDROID_TITLE_CONDENSED = "android:titleCondensed"
    private const val XML_MENU = "menu"
    private const val XML_ITEM = "item"

    override val viewType = BottomNavigationView::class.java

    override val supportedAttributes = setOf(ATTRIBUTE_MENU, ATTRIBUTE_APP_MENU, ATTRIBUTE_ID,
            ATTRIBUTE_ANDROID_ID, ATTRIBUTE_TITLE, ATTRIBUTE_ANDROID_TITLE, ATTRIBUTE_TITLE_CONDENSED,
            ATTRIBUTE_ANDROID_TITLE_CONDENSED, XML_MENU, XML_ITEM)

    override fun BottomNavigationView.transform(attrs: Map<String, Int>) {
        for (attributeName in attrs.keys) {
            if (attributeName == ATTRIBUTE_APP_MENU || attributeName == ATTRIBUTE_MENU) {

                val menuItemsStrings = PopupMenuHelper.getMenuItemsStrings(
                        resources = resources,
                        resId = attrs[attributeName] ?: 0
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