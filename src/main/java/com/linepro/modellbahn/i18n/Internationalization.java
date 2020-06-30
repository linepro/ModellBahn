package com.linepro.modellbahn.i18n;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({
    MessageTranslatorImpl.class,
    RequestLocaleFilter.class
})
@Component(PREFIX + "Internationalization")
public class Internationalization {
}
