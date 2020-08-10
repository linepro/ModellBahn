package com.linepro.modellbahn.i18n;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(PREFIX + "MessageTranslatorImpl")
@RequiredArgsConstructor
public class MessageTranslatorImpl implements MessageTranslator {

    @Autowired
    private final ResourceBundleLocator locator;

    @Override
    public String getMessage(String messageCode, Object... args) {
        try {
            ResourceBundle bundle = locator.getResourceBundle(getLocale());

            return new MessageFormat(bundle.getString(messageCode), getLocale()).format(args);
        } catch (Exception e) {
            String message = messageCode + " " + Stream.of(args).map(o -> o.toString()).collect(Collectors.joining(" "));

            log.error(message, e.getMessage());

            return message;
        }
    }

    private Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}
