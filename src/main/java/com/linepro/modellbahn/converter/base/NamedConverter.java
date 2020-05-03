package com.linepro.modellbahn.converter.base;

import com.linepro.modellbahn.entity.base.NamedItem;
import com.linepro.modellbahn.model.base.NamedItemModel;

public abstract class NamedConverter<M extends NamedItemModel, E extends NamedItem> extends ItemModelConverter<M, E> {

    public NamedConverter(ModelCreator<M> creator) {
        super(creator);
    }

    @Override
    public M convert(E source) {
        M model = super.convert(source);
        model.setName(source.getName());
        model.setBezeichnung(source.getBezeichnung());
        return model;
    }
}
