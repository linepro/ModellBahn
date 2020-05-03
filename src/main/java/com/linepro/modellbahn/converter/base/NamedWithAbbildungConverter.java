package com.linepro.modellbahn.converter.base;

import org.springframework.core.convert.converter.Converter;

import com.linepro.modellbahn.entity.base.NamedWithAbbildung;
import com.linepro.modellbahn.model.base.NamedWithAbbildungModel;

public abstract class NamedWithAbbildungConverter<M extends NamedWithAbbildungModel, E extends NamedWithAbbildung> extends NamedConverter<M,E> {

    public NamedWithAbbildungConverter(ModelCreator<M> creator) {
        super(creator);
    }

    @Override
    public M convert(E source) {
        M model = super.convert(source);
        model.setAbbildung(source.getAbbildung());
        return model;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends M> Converter<E, T> getConverter(Class<T> targetType) {
        return (Converter<E, T>) this;
    }
}
