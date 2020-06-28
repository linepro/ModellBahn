package com.linepro.modellbahn.i18n;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("MessageTranslatorImpl")
public class MessageTranslatorImpl implements MessageTranslator {

    @Override
    public String getMessage(String bundleName, String messageCode, Object... args) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName, getLocale());

            String format = bundle.getString(messageCode);

            return String.format(format, args);
        } catch (Exception e) {
            String message = messageCode + " "
                    + Stream.of(args).map(o -> o.toString()).collect(Collectors.joining(" "));

            log.error(message, e.getMessage());

            return message;
        }
    }

    Locale getLocale() {
      return ThreadLocale.get();
    }
}
