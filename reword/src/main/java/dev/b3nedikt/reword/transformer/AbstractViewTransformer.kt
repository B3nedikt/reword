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
        return attrs.extractAttributes(supportedAttributes)
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
}