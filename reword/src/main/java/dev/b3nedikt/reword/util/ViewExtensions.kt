package dev.b3nedikt.reword.util

import android.view.View

/**
 * Update the texts of a view.
 *
 * @param resId  the id of the text to update
 * @param setTextFunction function which updates the text of the view T
 * @return the transformed view.
 */
fun View.updateTexts(resId: Int, setTextFunction: (CharSequence) -> Unit) {
    setTextFunction(resources.getText(resId))
}