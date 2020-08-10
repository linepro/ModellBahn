package com.linepro.modellbahn.i18n;

import java.util.Locale;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.i18n.LocaleContextHolder;

public class ModellBahnInterpolator extends ResourceBundleMessageInterpolator {

    public ModellBahnInterpolator() {
        super(new PlatformResourceBundleLocator(Internationalization.APPLICATION_MESSAGES));
    }

    @Override
    public String interpolate(String template, Context context) {
        return super.interpolate(template, context, LocaleContextHolder.getLocale());
    }

    @Override
    public String interpolate(String template, Context context, Locale locale) {
        return super.interpolate(template, context, LocaleContextHolder.getLocale());
    }
}
