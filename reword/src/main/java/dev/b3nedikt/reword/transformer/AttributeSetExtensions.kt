package dev.b3nedikt.reword.transformer

import android.util.AttributeSet

fun <K, V> AttributeSet.associate(transform: (index: Int) -> Pair<K, V>): Map<K, V>  {
    return (0 until attributeCount).associate(transform)
}