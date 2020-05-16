package com.linepro.modellbahn.i18n;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({
    MessageTranslatorImpl.class,
    RequestLocaleFilter.class
})
@Component
public class Internationalization {
}
