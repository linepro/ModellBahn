package com.linepro.modellbahn.hateoas.impl;

import java.util.Collections;
import java.util.Map;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.NamedItemModel;

public class NamedFieldExtractor implements FieldExtractor {

    @Override
    public Map<String, Object> pathNames(Object model) {
        if (model instanceof NamedItemModel) {
            return Collections.singletonMap("{" + ApiNames.NAMEN + "}", ((NamedItemModel) model).getName());
        }
        return Collections.emptyMap();
    }
}
