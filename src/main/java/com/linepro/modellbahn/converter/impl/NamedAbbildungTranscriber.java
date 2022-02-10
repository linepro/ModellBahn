package com.linepro.modellbahn.converter.impl;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.entity.impl.NamedWithAbbildungItem;
import com.linepro.modellbahn.model.NamedWithAbbildungModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "NamedAbbildungTranscriber")
public class NamedAbbildungTranscriber<E extends NamedWithAbbildungItem, M extends NamedWithAbbildungModel> extends NamedTranscriber<E, M> {

    private final PathMapper pathMapper;

    @Override
    public M apply(E source, M destination) {

        if (source != null && destination != null) {
            destination.setAbbildung(pathMapper.convert(source.getAbbildung()));
        }

        return super.apply(source, destination);
    }

}
