package com.epam.pizza.util;


import javax.servlet.http.HttpServletRequest;

public class Locale {
    public static String getLocale(HttpServletRequest req) {
        Object locale = req.getSession().getAttribute("locale");
        if (locale != null) {
            return locale.toString();
        } else {
            return req.getLocale().toString();
        }
    }
}
