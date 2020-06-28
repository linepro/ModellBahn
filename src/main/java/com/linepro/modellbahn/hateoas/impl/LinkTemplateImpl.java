package com.linepro.modellbahn.hateoas.impl;

import java.util.Map;
import java.util.function.Predicate;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class LinkTemplateImpl implements LinkTemplate {

    private final String rel;
    
    private final String path;
    
    private final FieldsExtractor extractor;

    private final Predicate<RepresentationModel<?>> test;

    public LinkTemplateImpl(String rel, String path, FieldsExtractor extractor) {
        this(rel, path, extractor, null);
    }

    public LinkTemplateImpl(String rel, String path, FieldsExtractor extractor, Predicate<RepresentationModel<?>> test) {
        this.rel = rel;
        this.path = path.replaceAll(":[^}]+", "");
        this.extractor = extractor;
        this.test = test;
    }

    @Override
    public void apply(RepresentationModel<?> model) {
        if (test == null || test.test(model)) {
            Map<String,Object> names = extractor.pathNames(model);
            String path = this.path;

            for (String name : names.keySet()) {
                path = path.replace(name, String.valueOf(names.get(name)));
            }

            model.add(new Link(path, rel));
        }
    }
}