package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class DecoderKey extends BaseKey {

    private final String decoderId;

    public DecoderKey(final String decoderId) {
        this.decoderId = decoderId;
    }

    private String getDecoderId() {
        return decoderId;
    }
    
    @Override
    public void addCriteria(Query query) {
        query.setParameter(ApiNames.DECODER_ID, getDecoderId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.DECODER_ID, getDecoderId())
                .toString();
    }
}
