package dev.b3nedikt.reword.creator

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout
import dev.b3nedikt.reword.views.RewordTabLayout

/**
 * [ViewCreator] which creates a [RewordTabLayout].
 */
internal object TabLayoutViewCreator : ViewCreator<TabLayout> {

    override val viewName = TabLayout::class.qualifiedName.toString()

    override fun createView(context: Context, attrs: AttributeSet?): TabLayout {
        return RewordTabLayout(context, attrs)
    }
}