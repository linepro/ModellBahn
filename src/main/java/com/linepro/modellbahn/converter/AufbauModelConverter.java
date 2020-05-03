package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedWithAbbildungConverter;
import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.model.AufbauModel;

@Component
public class AufbauModelConverter extends NamedWithAbbildungConverter<AufbauModel, Aufbau> {

    public AufbauModelConverter() {
        super(() -> new AufbauModel());
    }
}
