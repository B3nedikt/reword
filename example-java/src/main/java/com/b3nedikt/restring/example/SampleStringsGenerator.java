package com.b3nedikt.restring.example;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import dev.b3nedikt.restring.PluralKeyword;

/**
 * Generates sample data
 */
public class SampleStringsGenerator {
    
    public Map<String, CharSequence> getStrings(@NonNull Locale locale) {
        final Map<String, CharSequence> map = new HashMap<>();
        if (locale == Locale.ENGLISH) {
            map.put("title", "Title (from restring).");
            map.put("subtitle", "Subtitle (from restring).");
            map.put("menu_entry_1", "Entry 1 EN (from restring)");
            map.put("menu_entry_2", "Entry 2 EN (from restring)");
        } else if (locale == Locale.US) {
            map.put("title", "Title US (from restring).");
            map.put("subtitle", "Subtitle US (from restring).");
            map.put("menu_entry_1", "Entry 1 US (from restring)");
            map.put("menu_entry_2", "Entry 2 US (from restring)");
        } else if (locale == Locales.LOCALE_AUSTRIAN_GERMAN) {
            map.put("title", "Titel (aus restring).");
            map.put("subtitle", "Untertitel (aus restring).");
            map.put("menu_entry_1", "Entry 1 AUS (from restring)");
            map.put("menu_entry_2", "Entry 2 AUS (from restring)");
        }
        return map;
    }

    @NonNull
    public Map<String, Map<PluralKeyword, CharSequence>> getQuantityStrings(@NonNull Locale locale) {
        final Map<String, Map<PluralKeyword, CharSequence>> map = new HashMap<>();

        final Map<PluralKeyword, CharSequence> quantityStrings = new HashMap<>();
        quantityStrings.put(PluralKeyword.ONE, "%d quantity string " + locale + " (from restring)");
        quantityStrings.put(PluralKeyword.OTHER, "%d quantity strings " + locale + " (from restring)");

        map.put("quantity_string", quantityStrings);
        return map;
    }

    @NonNull
    public Map<String, CharSequence[]> getStringArrays(@NonNull Locale locale) {
        final Map<String, CharSequence[]> map = new HashMap<>();

        final CharSequence[] stringArray = new CharSequence[2];
        stringArray[0] = "String Array 1 " + locale + " (from restring)";
        stringArray[1] = "String Array 2 " + locale + " (from restring)";

        map.put("string_array", stringArray);
        return map;
    }
}