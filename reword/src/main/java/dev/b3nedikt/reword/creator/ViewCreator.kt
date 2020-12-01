package dev.b3nedikt.reword.creator

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Creates views of type [T] given its [viewName].
 */
interface ViewCreator<out T : View> {

    /**
     * The name of the view of type [T] this [ViewCreator] will create.
     */
    val viewName: String

    /**
     * Creates a view of type [T].
     *
     * @param context The context the view is being created in.
     * @param attrs Inflation attributes as specified in XML file.
     */
    fun createView(context: Context, attrs: AttributeSet?): T
}