package com.linepro.modellbahn.hateoas.impl;

import java.util.Map;
import java.util.function.Predicate;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LinkTemplateImpl implements LinkTemplate {

    private final String rel;
    
    private final String path;

    private Predicate<RepresentationModel<?>> predicate;

    @Override
    public void apply(RepresentationModel<?> model, Map<String,Object> pathNames) {
        if (predicate == null || predicate.test(model)) {
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