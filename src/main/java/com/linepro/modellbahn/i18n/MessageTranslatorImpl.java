package com.linepro.modellbahn.i18n;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.slf4j.helpers.MessageFormatter;
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
            return MessageFormatter.arrayFormat(getBundle(messageCode, args.length), args).getMessage();
    }

    private String getBundle(String messageCode, int argCount) {
        if (messageCode.startsWith("{") && messageCode.endsWith("}")) {
            ResourceBundle bundle = locator.getResourceBundle(getLocale());

            String codeValue = messageCode.substring(1, messageCode.length()-1);

            try {
                return bundle.getString(codeValue);
            } catch (Exception e) {
                 log.error("Can't find message: {} for {}: {}",  messageCode, getLocale(), e.getMessage(), e);

                return codeValue + ":" + StringUtils.repeat(" {}", argCount);
            }
        }

        return messageCode;
    }

    private Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}
