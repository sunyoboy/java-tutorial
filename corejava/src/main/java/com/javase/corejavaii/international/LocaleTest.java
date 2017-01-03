package com.javase.corejavaii.international;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by root on 12/28/16.
 */
public class LocaleTest {

    public static void main(String[] args) {
        Locale[] locales = NumberFormat.getAvailableLocales();
        for(int i=0; i<locales.length; i++) {
            System.out.println(locales[i]);
        }
    }
}
