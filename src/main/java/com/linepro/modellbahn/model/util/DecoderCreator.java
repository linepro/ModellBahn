package com.linepro.modellbahn.model.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypAdress;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.model.impl.DecoderAdress;
import com.linepro.modellbahn.model.impl.DecoderCV;
import com.linepro.modellbahn.model.impl.DecoderFunktion;
import com.linepro.modellbahn.persistence.repository.IDecoderRepository;

@Component
public class DecoderCreator implements IDecoderCreator {

    private final IDecoderRepository persister;

    @Autowired
    public DecoderCreator(IDecoderRepository persister) {
        this.persister = persister;
    }

    @Override
    public IDecoder create(IDecoderTyp decoderTyp) throws Exception {
        Decoder decoder = new Decoder(null, decoderTyp, decoderTyp.getProtokoll(), null, decoderTyp.getBezeichnung(), decoderTyp.getFahrstufe(),
            DecoderStatus.FREI, false);
        
        decoder = persister.saveAndFlush(decoder);

        for (IDecoderTypAdress adress : decoderTyp.getAdressen()) {
            decoder.addAdress(new DecoderAdress(null, decoder, adress.getIndex(), adress.getAdressTyp(), adress.getAdress(), false));
        }

        for (IDecoderTypCV cv : decoderTyp.getCVs()) {
            decoder.addCV(new DecoderCV(null, decoder, cv, cv.getWerkseinstellung(), false));
        }

        for (IDecoderTypFunktion funktion : decoderTyp.getFunktionen()) {
            decoder.addFunktion(new DecoderFunktion(null, decoder, funktion, funktion.getBezeichnung(), false));
        }

        decoder = persister.saveAndFlush(decoder);

        return decoder;
    }
}
