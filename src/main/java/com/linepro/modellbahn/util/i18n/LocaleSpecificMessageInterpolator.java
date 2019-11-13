package com.linepro.modellbahn.util.i18n;

import java.util.Locale;

import javax.validation.MessageInterpolator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

public class LocaleSpecificMessageInterpolator implements MessageInterpolator {

  private final MessageInterpolator defaultInterpolator = new ResourceBundleMessageInterpolator();

  @Override
  public String interpolate(String message, Context context) {
    return defaultInterpolator.interpolate(message, context, getLocale());
  }

  @Override
  public String interpolate(String message, Context context, Locale locale) {
    return defaultInterpolator.interpolate(message, context, getLocale());
  }

  Locale getLocale() {
    return ThreadLocale.get();
  }
}
