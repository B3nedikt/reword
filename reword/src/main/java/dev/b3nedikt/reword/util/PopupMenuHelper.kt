package dev.b3nedikt.reword.util

import android.content.res.Resources
import android.util.AttributeSet
import android.util.Pair
import android.util.Xml
import org.xmlpull.v1.XmlPullParser


/**
 * A Helper class to parse menus, this can be used to create transformers for views which contain
 * menus, like the Toolbar or the BottomNavigationView
 */
object PopupMenuHelper {

    private const val ATTRIBUTE_ANDROID_TITLE = "android:title"
    private const val ATTRIBUTE_TITLE = "title"
    private const val ATTRIBUTE_ID = "id"
    private const val ATTRIBUTE_ANDROID_ID = "android:id"
    private const val ATTRIBUTE_TITLE_CONDENSED = "titleCondensed"
    private const val ATTRIBUTE_ANDROID_TITLE_CONDENSED = "android:titleCondensed"
    private const val XML_MENU = "menu"
    private const val XML_ITEM = "item"

    /**
     * Extracts [MenuItemStrings] from the menu with the provided [resId], returning an empty
     * map in case of a failure
     *
     * @param resources the resources from which the ids will get fetched
     * @param resId the id of the menu
     */
    fun getMenuItemsStrings(resources: Resources, resId: Int): Map<Int, MenuItemStrings> {

        if (resId == 0) return emptyMap()

        val parser = resources.getLayout(resId)
        val attrs = Xml.asAttributeSet(parser)

        return runCatching {
            parseMenu(parser, attrs)
        }.getOrDefault(emptyMap())
    }

    private fun parseMenu(parser: XmlPullParser, attrs: AttributeSet): Map<Int, MenuItemStrings> {

        val menuItems = mutableMapOf<Int, MenuItemStrings>()
        var eventType = parser.eventType
        var tagName: String

        // This loop will skip to the menu start tag
        do {
            if (eventType == XmlPullParser.START_TAG) {
                tagName = parser.name
                if (tagName == XML_MENU) {
                    eventType = parser.next()
                    break
                }

                error("Expecting menu, got $tagName")
            }
            eventType = parser.next()
        } while (eventType != XmlPullParser.END_DOCUMENT)

        while (true) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    tagName = parser.name
                    if (tagName == XML_ITEM) {
                        val item = parseMenuItem(attrs)
                        if (item != null) {
                            menuItems[item.first] = item.second
                        }
                    }
                }

                XmlPullParser.END_DOCUMENT -> break
            }

            eventType = parser.next()
        }
        return menuItems
    }

    private fun parseMenuItem(attrs: AttributeSet): Pair<Int, MenuItemStrings>? {
        var menuId = 0
        var menuItemStrings: MenuItemStrings? = null
        val attributeCount = attrs.attributeCount
        for (index in 0 until attributeCount) {
            if (attrs.getAttributeName(index) == ATTRIBUTE_ANDROID_ID
                    || attrs.getAttributeName(index) == ATTRIBUTE_ID) {
                menuId = attrs.getAttributeResourceValue(index, 0)
            } else if (attrs.getAttributeName(index) == ATTRIBUTE_ANDROID_TITLE
                    || attrs.getAttributeName(index) == ATTRIBUTE_TITLE) {
                val value = attrs.getAttributeValue(index)
                if (value == null || !value.startsWith("@")) break
                if (menuItemStrings == null) {
                    menuItemStrings = MenuItemStrings()
                }
                menuItemStrings.title = attrs.getAttributeResourceValue(index, 0)
            } else if (attrs.getAttributeName(index) == ATTRIBUTE_ANDROID_TITLE_CONDENSED
                    || attrs.getAttributeName(index) == ATTRIBUTE_TITLE_CONDENSED) {
                val value = attrs.getAttributeValue(index)
                if (value == null || !value.startsWith("@")) break
                if (menuItemStrings == null) {
                    menuItemStrings = MenuItemStrings()
                }
                menuItemStrings.titleCondensed = attrs.getAttributeResourceValue(index, 0)
            }
        }
        return if (menuId != 0 && menuItemStrings != null)
            Pair(menuId, menuItemStrings)
        else
            null
    }

    class MenuItemStrings {
        var title: Int = 0
        var titleCondensed: Int = 0
    }
}