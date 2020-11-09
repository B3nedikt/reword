package dev.b3nedikt.reword.transformer

import android.content.res.Resources
import android.util.AttributeSet
import android.util.Pair
import android.util.Xml
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.util.*

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
                PopupMenuHelper.getMenuItemsStrings(resources, attrs[attributeName] ?: 0).forEach {
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