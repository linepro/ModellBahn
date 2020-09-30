package com.linepro.modellbahn.hateoas.impl;

import static com.linepro.modellbahn.controller.impl.ApiNames.NAMEN;

import java.util.Collections;
import java.util.Map;

import com.linepro.modellbahn.model.NamedItemModel;

public class NamedFieldExtractor implements FieldsExtractor {

    private final FieldExtractor extractor;

    public NamedFieldExtractor() {
        this((m) -> (m instanceof NamedItemModel ? ((NamedItemModel) m).getName() : null));
    }

    public NamedFieldExtractor(FieldExtractor extractor) {
        this.extractor = extractor;
    }

    @Override
    public Map<String, Object> pathNames(Object model) {
        Object value = extractor.get(model);

        if (value != null) {
            return Collections.singletonMap(NAMEN, value);
        }

        return Collections.emptyMap();
    }
}
