package dev.b3nedikt.reword.transformer

import com.google.android.material.textfield.TextInputLayout

/**
 * A transformer which transforms [TextInputLayout]s, it only transforms the hint text.
 */
internal object TextInputLayoutViewTransformer : AbstractViewTransformer<TextInputLayout>() {

    private const val ATTRIBUTE_HINT = "hint"
    private const val ATTRIBUTE_ANDROID_HINT = "android:hint"

    override val viewType = TextInputLayout::class.java

    override val supportedAttributes = setOf(ATTRIBUTE_HINT, ATTRIBUTE_ANDROID_HINT)

    override fun TextInputLayout.transform(attrs: Map<String, Int>) {
        attrs.forEach { entry ->
            when (entry.key) {
                ATTRIBUTE_ANDROID_HINT, ATTRIBUTE_HINT -> updateTexts(entry.value, this::setHint)
            }
        }
    }
}