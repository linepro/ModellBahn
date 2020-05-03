package com.linepro.modellbahn.model.base;

import java.io.Serializable;

public interface ItemModel extends Serializable {
    Boolean getDeleted();
    void setDeleted(Boolean deleted);
}
