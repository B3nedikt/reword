package dev.b3nedikt.reword.transformer

import android.util.AttributeSet
import android.view.View

/**
 * A [ViewTransformer] transforms a view
 */
interface ViewTransformer<in T : View> {

    /**
     * The type of view supported by this [ViewTransformer]
     */
    val viewType: Class<in T>

    /**
     * The attributes which contain text that can be updated with this [ViewTransformer]
     */
    val supportedAttributes: Set<String>

    /**
     * Transforms the view
     *
     * @param attrs a [Map] of view attributes with the attribute value from its [AttributeSet]s
     * as the key and the attribute resource value as the value
     */
    fun T.transform(attrs: Map<String, Int>)
}