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

    private final Predicate<RepresentationModel<?>> test;

    public LinkTemplateImpl(String rel, String path) {
        this(rel, path, null);
    }

    public LinkTemplateImpl(String rel, String path, Predicate<RepresentationModel<?>> test) {
        this.rel = rel;
        this.path = path.replaceAll(":[^}]+", "");
        this.test = test;
    }

    @Override
    public void apply(RepresentationModel<?> model, Map<String,Object> pathNames) {
        if (test == null || test.test(model)) {
            Link link = getLink(pathNames);

            if (link != null) {
                model.add(link);
            }
        }
    }

    protected Link getLink(Map<String,Object> pathNames) {
        String path = getPath();
        for (String name : pathNames.keySet()) {
            path = path.replace(name, String.valueOf(pathNames.get(name)));
        }
        return new Link(path, rel);
    }
}