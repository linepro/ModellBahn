package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.persistence.DBNames;

public class DecoderCVKey extends BaseKey {

    protected final IDecoder decoder;

    protected final Integer cv;

    public DecoderCVKey(final IDecoder decoder, final Integer cv) {
        this.decoder = decoder;
        this.cv = cv;
    }

    public IDecoder getDecoder() {
        return decoder;
    }

    public Integer getCv() {
        return cv;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.DECODER, getDecoder());
        query.setParameter(DBNames.CV, getCv());
    }
}
