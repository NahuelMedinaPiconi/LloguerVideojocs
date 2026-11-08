/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nahuelcompany.gestiovideojocs;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author gerre
 */
public final class Missatges {
    public static final Locale LOCALE = Locale.forLanguageTag("ca-ES");

    private static final ResourceBundle BUNDLE =
            ResourceBundle.getBundle("com.nahuelcompany.gestiovideojocs.Bundle", LOCALE);

    private Missatges() { }

    public static String get(String key) {
        return BUNDLE.getString(key);
    }
}
