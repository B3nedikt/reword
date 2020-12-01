package dev.b3nedikt.reword.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import dev.b3nedikt.reword.R

/**
 * A custom [TabLayout] which copies the Reword view tag from the [RewordTabItem] dummy view
 * to the newly created [TabLayout.TabView] when it gets added to the [RewordTabLayout]
 * and updates the text of the new [TabLayout.TabView] using the [RewordTabItem.textResourceId].
 */
internal class RewordTabLayout(
        context: Context,
        attrs: AttributeSet?
) : TabLayout(context, attrs) {

    override fun addView(child: View?) {
        super.addView(child)
        copyRewordTagAndUpdateText(child)
    }

    override fun addView(child: View?, index: Int) {
        super.addView(child, index)
        copyRewordTagAndUpdateText(child, index)
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        super.addView(child, params)
        copyRewordTagAndUpdateText(child)
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)
        copyRewordTagAndUpdateText(child, index)
    }

    private fun copyRewordTagAndUpdateText(child: View?, index: Int = tabCount - 1) {
        copyRewordTag(child, index)
        updateText(child, index)
    }

    private fun copyRewordTag(child: View?, index: Int) {
        getTabAt(index)
                ?.view
                ?.setTag(R.id.view_tag, child?.getTag(R.id.view_tag))
    }

    private fun updateText(child: View?, index: Int) {

        val resId = (child as? RewordTabItem)?.textResourceId

        if (resId != null) {
            getTabAt(index)?.text = context.resources.getText(resId)
        }
    }
}