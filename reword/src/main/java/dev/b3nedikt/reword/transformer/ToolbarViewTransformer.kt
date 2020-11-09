package dev.b3nedikt.reword.transformer

import android.os.Build
import android.widget.Toolbar
import androidx.annotation.RequiresApi

/**
 * A transformer which transforms [Toolbar]: it transforms the text set as title.
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
internal object ToolbarViewTransformer : AbstractViewTransformer<Toolbar>() {

    private const val ATTRIBUTE_TITLE = "title"
    private const val ATTRIBUTE_SUBTITLE = "subtitle"
    private const val ATTRIBUTE_ANDROID_TITLE = "android:title"
    private const val ATTRIBUTE_ANDROID_SUBTITLE = "android:subtitle"
    private const val ATTRIBUTE_MENU = "menu"
    private const val ATTRIBUTE_APP_MENU = "app:menu"
    private const val ATTRIBUTE_ID = "id"
    private const val ATTRIBUTE_ANDROID_ID = "android:id"
    private const val ATTRIBUTE_TITLE_CONDENSED = "titleCondensed"
    private const val ATTRIBUTE_ANDROID_TITLE_CONDENSED = "android:titleCondensed"
    private const val XML_MENU = "menu"
    private const val XML_ITEM = "item"

    override val viewType = Toolbar::class.java

    override val supportedAttributes = setOf(ATTRIBUTE_MENU, ATTRIBUTE_APP_MENU, ATTRIBUTE_ID,
            ATTRIBUTE_ANDROID_ID, ATTRIBUTE_ANDROID_TITLE, ATTRIBUTE_TITLE_CONDENSED,
            ATTRIBUTE_ANDROID_TITLE_CONDENSED, XML_MENU, XML_ITEM, ATTRIBUTE_TITLE, ATTRIBUTE_ANDROID_SUBTITLE, ATTRIBUTE_SUBTITLE)

    override fun Toolbar.transform(attrs: Map<String, Int>) {
        attrs.forEach { entry ->
            when (entry.key) {
                ATTRIBUTE_TITLE, ATTRIBUTE_ANDROID_TITLE -> updateTexts(entry.value, this::setTitle)
                ATTRIBUTE_SUBTITLE, ATTRIBUTE_ANDROID_SUBTITLE -> updateTexts(entry.value, this::setSubtitle)
                ATTRIBUTE_MENU, ATTRIBUTE_APP_MENU -> {


                    val menuItemsStrings = PopupMenuHelper.getMenuItemsStrings(
                            resources = resources,
                            resId = attrs[entry.key] ?: 0
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