package dev.b3nedikt.reword.transformer

import android.widget.SearchView
import dev.b3nedikt.reword.util.updateTexts


/**
 * A [ViewTransformer] which transforms the framework [SearchView]
 */
internal object SearchViewViewTransformer : ViewTransformer<SearchView> {

    private const val ATTRIBUTE_QUERY_HINT = "queryHint"
    private const val ATTRIBUTE_ANDROID_QUERY_HINT = "android:queryHint"

    override val viewType = SearchView::class.java

    override val supportedAttributes = setOf(ATTRIBUTE_QUERY_HINT, ATTRIBUTE_ANDROID_QUERY_HINT)

    override fun SearchView.transform(attrs: Map<String, Int>) {
        attrs.forEach { attribute ->
            when (attribute.key) {
                ATTRIBUTE_QUERY_HINT, ATTRIBUTE_ANDROID_QUERY_HINT -> updateTexts(attribute.value, this::setQueryHint)
            }
        }
    }
}