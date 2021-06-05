package com.linepro.modellbahn.i18n;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LocaleSetterImpl implements LocaleSetter {

    protected static final List<Locale> LOCALES = Arrays.asList(Locale.getAvailableLocales());

    public static final String LOCALE = "locale";

    @Override
    public void setLocale(HttpSession session, String languageTag) {
        Locale locale = getLocale(languageTag);

        if (locale != null) {
            try {
                if (session == null) {
                    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                    if (attr != null) {
                        HttpServletRequest request = attr.getRequest();
                        if (request != null) {
                            session = request.getSession();
                        }
                    }
                }

                if (session != null) {
                    session.setAttribute(LOCALE, session);
                }
            } catch (Exception e) {
                log.error("Cannot set locale {} on session: {}", languageTag, e.getMessage());
            }

            LocaleContextHolder.setLocale(locale);
        }
    }

    protected Locale getLocale(String languageTag) {
        try {
            List<Locale.LanguageRange> languageRanges = Locale.LanguageRange.parse(languageTag.replaceAll("_", "-") + ";q=1.0");

            List<Locale> results = Locale.filter(languageRanges, LOCALES);
            if (!results.isEmpty()) {
                return results.get(0);
            }
        } catch (Exception e) {
            log.error("Cannot determine locale for {}: {}", languageTag, e.getMessage());
        }

        return null;
    }
}
