function forwardLocale(locale) {
    if (locale == "ru_RU") {
        window.location = "/locale/locale?locale=" + locale;
    } else if (locale == "en_US") {
        window.location = "/locale/locale?locale=" + locale;
    }

}