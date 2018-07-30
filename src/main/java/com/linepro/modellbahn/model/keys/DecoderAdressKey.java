package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.persistence.DBNames;

public class DecoderAdressKey extends BaseKey {

    protected final IDecoder decoder;
    
    protected final AdressTyp adressTyp;
    
    protected final Integer adress;

    public DecoderAdressKey(final IDecoder decoder, final AdressTyp adressTyp, final Integer adress) {
        this.decoder = decoder;
        this.adressTyp = adressTyp;
        this.adress = adress;
    }

    public IDecoder getDecoder() {
        return decoder;
    }

    public AdressTyp getAdressTyp() {
        return adressTyp;
    }

    public Integer getAdress() {
        return adress;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.DECODER, getDecoder());
        query.setParameter(DBNames.ADRESS_TYP, getAdressTyp());
        query.setParameter(DBNames.ADRESS, getAdress());
    }
}
