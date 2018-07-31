package com.linepro.modellbahn.model.util;

import com.linepro.modellbahn.model.IDescribedEnum;

/**
 * Connector. Enumeration of Decoder connections
 * 
 * @author $Author:$
 * @version $Id:$
 */
public enum Connector implements IDescribedEnum {

    EINGEBAUT("Builin / Hardwired."),

    NEM651("NEM 651 6 pole."),

    NEM652("NEM 652 8 pole."),

    MTC21("MTC21."),

    PluX12("PluX12."),

    PluX16("PluX16."),

    PluX22("PluX22.");

    protected final String description;
    
    private Connector(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
