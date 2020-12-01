package dev.b3nedikt.reword

import android.util.AttributeSet
import android.view.View
import dev.b3nedikt.viewpump.InflateResult
import dev.b3nedikt.viewpump.Interceptor

/**
 * The ViewPump [Interceptor] needed for [Reword].
 */
object RewordInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): InflateResult {

        val name = chain.request().name
        val context = chain.request().context
        val attrs = chain.request().attrs

        val newView = Reword.viewTransformerManager.createView(
                name = name,
                context = context,
                attrs = attrs
        )

        if (newView != null) {
            return InflateResult(
                    view = rewordView(newView, attrs),
                    name = name,
                    context = context,
                    attrs = attrs
            )
        }

        return chain.proceed(chain.request()).let { inflateResult ->
            inflateResult.copy(view = rewordView(inflateResult.view, inflateResult.attrs))
        }
    }

    private fun rewordView(view: View?, attributeSet: AttributeSet?): View? = when {
        view != null && attributeSet != null -> view.reword(attributeSet)
        else -> view
    }
}

private fun View.reword(attrs: AttributeSet): View = Reword.viewTransformerManager.transform(this, attrs)