package dev.b3nedikt.reword.transformer

import android.util.AttributeSet
import android.view.View

/**
 * A [ViewTransformer] skeleton.
 */
abstract class AbstractViewTransformer<T : View> : ViewTransformer<T> {

    /**
     * Extracts the attributes of a [View] from its [AttributeSet]
     *
     * @param view  we want to update the text of
     * @param attrs attributes of the view.
     * @return a [Map] of attributes as keys and their value
     */
    override fun extractAttributes(view: View, attrs: AttributeSet): Map<String, Int> {
        return attrs.associate { index ->
            val name = attrs.getAttributeName(index)
            if (name in supportedAttributes) name to getResourceId(attrs, index)
            else name to -1
        }.filterValues { it != -1 }
    }

    /**
     * Update the texts of a view.
     *
     * @param resId  the id of the text to update
     * @param setTextFunction function which updates the text of the view T
     * @return the transformed view.
     */
    protected fun T.updateTexts(resId: Int, setTextFunction: (CharSequence) -> Unit) {
        setTextFunction(resources.getString(resId))
    }

    /**
     * Returns the attribute resource value if it is a valid resource id, else returns -1.
     *
     * @param attrs  the [AttributeSet] of the view
     * @param index the index of the attribute value in the [AttributeSet] of the view
     * @return the resource id if the value at the given index is a resource id, else -1.
     */
    private fun getResourceId(attrs: AttributeSet, index: Int): Int {
        val value: String? = attrs.getAttributeValue(index)
        if (isValidResourceId(value)) {
            return attrs.getAttributeResourceValue(index, -1)
        }
        return -1
    }

    private fun isValidResourceId(value: String?) =
            value?.run { startsWith("@") && equals("@0").not() } ?: false
}