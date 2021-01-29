package dev.b3nedikt.reword

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import dev.b3nedikt.reword.creator.ViewCreator
import dev.b3nedikt.reword.transformer.ViewTransformer
import dev.b3nedikt.reword.util.extractAttributes

/**
 * Manages all view transformers
 */
internal class ViewTransformerManager {

    private val transformers = mutableListOf<ViewTransformer<View>>()

    private val creators = mutableListOf<ViewCreator<View>>()

    /**
     * Register a new view viewTransformer to transform views
     *
     * @param viewTransformer to be added to transformers list.
     */
    fun <T : View> registerTransformer(viewTransformer: ViewTransformer<T>) {
        @Suppress("UNCHECKED_CAST")
        transformers.add(viewTransformer as ViewTransformer<View>)
    }

    /**
     * Register a new view viewCreator to inflate views
     *
     * @param viewCreator to be added to the transformers list.
     */
    fun registerViewCreator(viewCreator: ViewCreator<View>) {
        creators.add(viewCreator)
    }

    /**
     * Creates a new view, or returns null if no [ViewCreator] for this specific view is registered.
     *
     * @param name name of the view
     * @param attrs attributes of the view.
     * @return the newly created view.
     */
    fun createView(name: String, context: Context, attrs: AttributeSet?): View? =
            creators.find { it.viewName == name }
                    ?.createView(context, attrs)

    /**
     * Transforms a already inflated view, returns the view unchanged if no [ViewTransformer] for
     * this specific view is registered.
     *
     * @param view  to be transformed.
     * @param attrs attributes of the view.
     * @return the transformed view.
     */
    fun transform(view: View, attrs: AttributeSet): View =
            transformers.find { it.viewType.isInstance(view) }
                    ?.run {
                        val extractedAttributes = attrs.extractAttributes(supportedAttributes)

                        view.setTag(R.id.view_tag, extractedAttributes)
                        view.transform(extractedAttributes)

                        view
                    }
                    ?: view

    /**
     * Transforms all children of the provided view.
     * Tries to find proper transformers for each child view, and if exists, it will apply them on
     * the view in place. Implemented not recursive, to ensure we only visit each child once
     * for efficiency.
     */
    fun transformChildren(parentView: View) {
        val visited = mutableListOf<View>()
        val unvisited = mutableListOf(parentView)

        while (unvisited.isNotEmpty()) {
            val child = unvisited.removeAt(0)

            @Suppress("UNCHECKED_CAST")
            val attrsTag = child.getTag(R.id.view_tag) as? Map<String, Int>

            attrsTag?.let { attrs ->
                transformers.find { it.viewType.isInstance(child) }
                        ?.run { child.transform(attrs) }
            }

            visited.add(child)
            if (child !is ViewGroup) continue
            val childCount = child.childCount
            for (i in 0 until childCount) unvisited.add(child.getChildAt(i))
        }
    }
}