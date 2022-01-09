package com.b3nedikt.restring.example;

import java.util.*;

class Locales {
    final static Locale LOCALE_AUSTRIAN_GERMAN = new Locale("de", "AT");

    final static List<Locale> APP_LOCALES =
            Arrays.asList(Locale.ENGLISH, Locale.US, Locales.LOCALE_AUSTRIAN_GERMAN);
}
