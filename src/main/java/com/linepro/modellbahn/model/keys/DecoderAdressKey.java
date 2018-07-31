package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class DecoderAdressKey extends BaseKey {

    protected final IDecoder decoder;
    
    protected final Integer offset;

    public DecoderAdressKey(final IDecoder decoder, final Integer offset) {
        this.decoder = decoder;
        this.offset = offset;
    }

    public IDecoder getDecoder() {
        return decoder;
    }

    public Integer getOffset() {
        return offset;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.DECODER, getDecoder());
        query.setParameter(DBNames.OFFSET, getOffset());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.DECODER, getDecoder())
                .append(ApiNames.OFFSET, getOffset())
                .toString();
    }
}
