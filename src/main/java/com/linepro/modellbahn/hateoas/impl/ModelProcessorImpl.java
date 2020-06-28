package com.linepro.modellbahn.hateoas.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

public class ModelProcessorImpl<M extends RepresentationModel<M>> implements ModelProcessor<M> {
    
    protected final List<LinkTemplate> templates;

    public ModelProcessorImpl(LinkTemplate...templates) {
        this.templates = new ArrayList<LinkTemplate>(Arrays.asList(templates));
    }

    public void addLinks(LinkTemplate...templates) {
        this.templates.addAll(Arrays.asList(templates));
    }

    @Override
    public M process(M model) {
        templates.forEach(t -> t.apply(model));
        return model;
    }
}