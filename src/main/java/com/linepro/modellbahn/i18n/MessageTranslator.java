package com.linepro.modellbahn.i18n;

public interface MessageTranslator {

    String getMessage(String messageCode, Object... args);
}
