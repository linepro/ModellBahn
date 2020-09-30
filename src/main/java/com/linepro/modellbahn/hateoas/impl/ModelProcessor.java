package com.linepro.modellbahn.hateoas.impl;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.RepresentationModelProcessor;

public interface ModelProcessor<M extends RepresentationModel<M>> extends  RepresentationModelProcessor<M>, RepresentationModelAssembler<M, M> {
    @SuppressWarnings("unchecked")
    default M process(Object model) {
        return process((M) model);
    }

    M process(M model);

    @Override
    default M toModel(M model) {
        return process(model);
    }
}
