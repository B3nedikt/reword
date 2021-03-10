package dev.b3nedikt.reword.transformer

import androidx.appcompat.widget.SearchView
import dev.b3nedikt.reword.util.updateTexts


/**
 * A [ViewTransformer] which transforms the androidX [SearchView]
 */
internal object AppCompatSearchViewViewTransformer : ViewTransformer<SearchView> {

    private const val ATTRIBUTE_QUERY_HINT = "queryHint"
    private const val ATTRIBUTE_DEFAULT_QUERY_HINT = "defaultQueryHint"
    private const val ATTRIBUTE_APP_QUERY_HINT = "app:queryHint"
    private const val ATTRIBUTE_APP_DEFAULT_QUERY_HINT = "app:defaultQueryHint"

    override val viewType = SearchView::class.java

    override val supportedAttributes = setOf(
            ATTRIBUTE_QUERY_HINT, ATTRIBUTE_DEFAULT_QUERY_HINT, ATTRIBUTE_APP_QUERY_HINT,
            ATTRIBUTE_APP_DEFAULT_QUERY_HINT)

    override fun SearchView.transform(attrs: Map<String, Int>) {
        attrs.forEach { attribute ->
            when (attribute.key) {
                ATTRIBUTE_QUERY_HINT, ATTRIBUTE_APP_QUERY_HINT -> updateTexts(attribute.value, this::setQueryHint)
                ATTRIBUTE_DEFAULT_QUERY_HINT, ATTRIBUTE_APP_DEFAULT_QUERY_HINT -> updateTexts(attribute.value, this::setQueryHint)
            }
        }
    }
}