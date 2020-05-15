package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;

public class DecoderTypModelMutator implements Mutator<DecoderTypModel,DecoderTyp> {
    
    public DecoderTyp apply(DecoderTypModel source, DecoderTyp destination, int depth) {
        //destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }

    @Override
    public DecoderTyp get() {
        return new DecoderTyp();
    }
}
