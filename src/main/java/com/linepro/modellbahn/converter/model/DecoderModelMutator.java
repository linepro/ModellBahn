package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;

public class DecoderModelMutator implements Mutator<DecoderModel, Decoder> {
    
    public Decoder apply(DecoderModel source, Decoder destination, int depth) {
        //destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }

    @Override
    public Decoder get() {
        return new Decoder();
    }
}
