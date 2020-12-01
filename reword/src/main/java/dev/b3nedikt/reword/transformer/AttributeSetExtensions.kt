package dev.b3nedikt.reword.transformer

import android.util.AttributeSet

/**
 * Extracts the attributes of a [View] from its [AttributeSet]
 *
 * @param attributeNames set of names of attributes to extract
 * @return a [Map] of attributes as keys and their value
 */
fun AttributeSet.extractAttributes(attributeNames: Set<String>): Map<String, Int> {
    return associate { index ->
        val name = getAttributeName(index)
        if (name in attributeNames) name to getResourceId(this, index)
        else name to -1
    }.filterValues { it != -1 }
}

/**
 * Returns a [Map] containing key-value pairs provided by [transform] function
 * applied to elements of the given [AttributeSet].
 *
 * If any of two pairs would have the same key the last one gets added to the map.
 */
fun <K, V> AttributeSet.associate(transform: (index: Int) -> Pair<K, V>): Map<K, V>  {
    return (0 until attributeCount).associate(transform)
}

private fun getResourceId(attrs: AttributeSet, index: Int): Int {
    val value: String? = attrs.getAttributeValue(index)
    if (isValidResourceId(value)) {
        return attrs.getAttributeResourceValue(index, -1)
    }
    return -1
}

private fun isValidResourceId(value: String?) =
        value?.run { startsWith("@") && equals("@0").not() } ?: false
