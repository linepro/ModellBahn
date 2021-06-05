package com.linepro.modellbahn.i18n;

import javax.servlet.http.HttpSession;

public interface LocaleSetter {

    void setLocale(HttpSession httpSession, String locale);
}
