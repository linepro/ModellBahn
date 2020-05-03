package com.linepro.modellbahn.model.base;

import org.springframework.hateoas.RepresentationModel;

@SuppressWarnings("serial")
public abstract class ItemModelImpl<M extends ItemModelImpl<M>> extends RepresentationModel<M> implements ItemModel {
}
