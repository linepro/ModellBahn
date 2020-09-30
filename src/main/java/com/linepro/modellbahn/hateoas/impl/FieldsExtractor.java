package com.linepro.modellbahn.hateoas.impl;

import java.util.Map;

public interface FieldsExtractor {
    Map<String,Object> pathNames(Object model);
}
