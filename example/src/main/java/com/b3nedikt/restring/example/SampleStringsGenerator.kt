package com.b3nedikt.restring.example

import dev.b3nedikt.restring.PluralKeyword
import java.util.*

/**
 * Generates example strings
 */
object SampleStringsGenerator {

    fun getStrings(locale: Locale): Map<String, CharSequence> {
        return mapOf<String, CharSequence>(
                "title" to "Title $locale (from restring)",
                "menu_entry_1" to "Entry 1 $locale (from restring)",
                "menu_entry_2" to "Entry 2 $locale (from restring)",
                "menu_entry_3" to "Entry 3 $locale (from restring)",
                "menu_entry_4" to "Entry 4 $locale (from restring)",
                "menu_subtitle" to "Subtitle $locale (from restring)",
                "header_title" to "Header title $locale (from restring)",
                "header_text" to "Header text $locale (from restring)",
        )
    }

    fun getStringArrays(locale: Locale): Map<String, Array<CharSequence>> {
        return mapOf(
                "string_array"
                        to
                        arrayOf("String Array 1 $locale (from restring)",
                                "String Array 2 $locale (from restring)")
        )
    }

    fun getQuantityStrings(locale: Locale): Map<String, Map<PluralKeyword, CharSequence>> {
        return mapOf(
                "quantity_string"
                        to
                        mapOf(PluralKeyword.ONE to "%d quantity string $locale (from restring)",
                                PluralKeyword.OTHER to "%d quantity strings $locale (from restring)")
        )
    }
}