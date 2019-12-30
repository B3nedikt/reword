package dev.b3nedikt.reword.transformer

import android.util.AttributeSet

/**
 * Returns a [Map] containing key-value pairs provided by [transform] function
 * applied to elements of the given [AttributeSet].
 *
 * If any of two pairs would have the same key the last one gets added to the map.
 */
fun <K, V> AttributeSet.associate(transform: (index: Int) -> Pair<K, V>): Map<K, V>  {
    return (0 until attributeCount).associate(transform)
}