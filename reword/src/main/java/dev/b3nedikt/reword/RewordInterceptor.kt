package dev.b3nedikt.reword

import android.util.AttributeSet
import android.util.Log
import android.view.View
import dev.b3nedikt.viewpump.InflateResult
import dev.b3nedikt.viewpump.Interceptor

/**
 * The ViewPump [Interceptor] needed for [Reword]
 */
object RewordInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): InflateResult =
            chain.proceed(chain.request()).let { inflateResult ->
                Log.wtf("GGG", inflateResult.name+"   "+ inflateResult.view.toString())
                inflateResult.copy(view = rewordView(inflateResult.view, inflateResult.attrs))
            }

    private fun rewordView(view: View?, attributeSet: AttributeSet?): View? = when {
        view != null && attributeSet != null -> view.reword(attributeSet)
        else -> view
    }
}

private fun View.reword(attrs: AttributeSet): View = Reword.viewTransformerManager.transform(this, attrs)