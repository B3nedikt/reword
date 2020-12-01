package dev.b3nedikt.reword.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabItem

/**
 * A [TabItem] dummy view, this is like the [TabItem] purely a container and never gets
 * added to the view hierarchy.
 */
@SuppressLint("ViewConstructor")
internal class RewordTabItem(
        context: Context,
        attrs: AttributeSet?,
        val textResourceId: Int?
) : TabItem(context, attrs)