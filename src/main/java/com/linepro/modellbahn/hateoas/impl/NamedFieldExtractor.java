package com.linepro.modellbahn.hateoas.impl;

import java.util.Collections;
import java.util.Map;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.NamedItemModel;

public class NamedFieldExtractor implements FieldsExtractor {

    public static final String NAME = "{" + ApiNames.NAMEN + "}";

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
            return Collections.singletonMap(NAME, value);
        }

        return Collections.emptyMap();
    }
}
