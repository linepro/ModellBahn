package com.linepro.modellbahn.converter.base;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import com.linepro.modellbahn.entity.base.Item;
import com.linepro.modellbahn.model.base.ItemModel;

public abstract class ItemModelConverter<M extends ItemModel, E extends Item> implements ConverterFactory<E, M>, Converter<E, M> {

    private final ModelCreator<M> creator;

    public ItemModelConverter(ModelCreator<M> creator) {
        this.creator = creator;
    }

    @Override
    public M convert(E source) {
        M model = creator.create();
        model.setDeleted(source.getDeleted());
        return model;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends M> Converter<E, T> getConverter(Class<T> targetType) {
        return (Converter<E, T>) this;
    }
}
