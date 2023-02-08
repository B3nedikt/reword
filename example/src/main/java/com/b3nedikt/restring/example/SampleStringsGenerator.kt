package com.b3nedikt.restring.example

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
            "menu_entry_5" to "Entry 5 $locale (from restring)",
            "menu_entry_6" to "Entry 6 $locale (from restring)",
            "menu_subtitle_1" to "Subtitle 1 $locale (from restring)",
            "menu_subtitle_2" to "Subtitle 2 $locale (from restring)",
            "header_title" to "Header title $locale (from restring)",
            "header_text" to "Header text $locale (from restring)",
            "query_hint" to "Query hint $locale (from restring)",
            "default_query_hint" to "Default query hint $locale (from restring)"
        )
    }
}