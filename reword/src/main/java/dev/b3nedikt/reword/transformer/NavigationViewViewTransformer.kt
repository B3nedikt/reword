package dev.b3nedikt.reword.transformer

import com.google.android.material.navigation.NavigationView
import dev.b3nedikt.reword.util.PopupMenuHelper

/**
 * A [ViewTransformer] which transforms the androidX [NavigationView]
 */
internal object NavigationViewViewTransformer : ViewTransformer<NavigationView> {

    private const val ATTRIBUTE_MENU = "menu"
    private const val ATTRIBUTE_APP_MENU = "app:menu"

    override val viewType = NavigationView::class.java

    override val supportedAttributes = setOf(
            ATTRIBUTE_MENU, ATTRIBUTE_APP_MENU)

    override fun NavigationView.transform(attrs: Map<String, Int>) {
        attrs.forEach { attribute ->
            when (attribute.key) {
                ATTRIBUTE_MENU, ATTRIBUTE_APP_MENU -> {

                    val menuItemsStrings = PopupMenuHelper.getMenuItemsStrings(
                            resources = resources,
                            resId = attrs[attribute.key] ?: 0
                    )

                    menuItemsStrings.forEach {
                        if (it.value.title != 0) {
                            menu.findItem(it.key)?.title = resources.getString(it.value.title)
                        }
                        if (it.value.titleCondensed != 0) {
                            menu.findItem(it.key)?.titleCondensed = resources.getString(it.value.titleCondensed)
                        }
                    }
                }
            }
        }
    }
}