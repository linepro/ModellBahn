package com.linepro.modellbahn.util.i18n;

import java.util.Locale;

public class ThreadLocale {

  public static final ThreadLocal<Locale> THREAD_LOCAL = new ThreadLocal<>();

  public static Locale get() {
    return (THREAD_LOCAL.get() == null) ? Locale.getDefault() : THREAD_LOCAL.get();
  }

  public static void set(Locale locale) {
    THREAD_LOCAL.set(locale);
  }

  public static void unset() {
    THREAD_LOCAL.remove();
  }
}
