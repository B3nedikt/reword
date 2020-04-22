package dev.b3nedikt.reword

import android.view.View
import dev.b3nedikt.reword.transformer.*


/**
 * Reword is a library to update the texts of views when the apps texts have
 * changed due to a language change or an update of the apps string resources with a lib like
 * restring.
 */
object Reword {

    internal val viewTransformerManager: ViewTransformerManager by lazy {
        ViewTransformerManager().apply {
            registerTransformer(TextViewViewTransformer)
            registerTransformer(ToolbarViewTransformer)
            registerTransformer(SupportToolbarViewTransformer)
            registerTransformer(BottomNavigationViewViewTransformer)
            registerTransformer(TextInputLayoutViewTransformer)
        }
    }

    /**
     * Register a new view transformer. Use this if you want to update the texts of a custom view.
     * @param viewTransformer [ViewTransformer]s to register
     */
    @JvmStatic
    fun addViewTransformer(vararg viewTransformer: ViewTransformer<*>) =
            viewTransformer.forEach { viewTransformerManager.registerTransformer(it) }

    /**
     * Will update the text of all views in the view hierarchy below the provided parent view.
     * Can be used to update the texts of a activity or fragment without restarting it. To do this
     * just use the root view of the activity or fragment.
     *
     * @param parent the parent view of the views you want to update the texts of
     */
    @JvmStatic
    fun reword(parent: View) {
        viewTransformerManager.transformChildren(parent)
    }
}