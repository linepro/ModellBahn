package com.linepro.modellbahn.hateoas.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.hateoas.RepresentationModel;

public class ModelProcessorImpl<M extends RepresentationModel<M>> implements ModelProcessor<M> {
    
    protected final FieldExtractor extractor;

    protected final List<LinkTemplate> templates;

    public ModelProcessorImpl(FieldExtractor extractor, LinkTemplate...templates) {
        this.extractor = extractor;
        this.templates = new ArrayList<LinkTemplate>(Arrays.asList(templates));
    }

    public void addLinks(LinkTemplate...templates) {
        this.templates.addAll(Arrays.asList(templates));
    }

    @Override
    public M process(M model) {
        Map<String,Object> paths = extractor.pathNames(model);

        templates.forEach(t -> t.apply(model, paths));
        return model;
    }
}