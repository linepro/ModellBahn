package com.linepro.modellbahn.hateoas.impl;

import java.util.Map;

public interface FieldExtractor {
    Map<String,Object> pathNames(Object model);
}
