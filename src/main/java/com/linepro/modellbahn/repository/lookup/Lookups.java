package com.linepro.modellbahn.repository.lookup;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({
    ArtikelLookup.class,
    DecoderLookup.class,
    DecoderTypLookup.class,
    ItemLookup.class,
    ProduktLookup.class,
    VorbildLookup.class
})
@Component
public class Lookups {
}
