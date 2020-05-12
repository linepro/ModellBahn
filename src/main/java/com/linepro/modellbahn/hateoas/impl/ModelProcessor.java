package com.linepro.modellbahn.hateoas.impl;

import org.springframework.hateoas.RepresentationModel;

import com.linepro.modellbahn.hateoas.LinkBuilder;

public abstract class ModelProcessor<M extends RepresentationModel<M>> {

    private final LinkBuilder<M> linkBuilder;

    public ModelProcessor(LinkBuilder<M> linkBuilder) {
        this.linkBuilder = linkBuilder;
    }

    public M process(M model) {
        model.add(linkBuilder.getLinks(model));

        return model;
    }
}