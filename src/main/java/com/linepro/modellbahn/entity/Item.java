package com.linepro.modellbahn.entity;

import com.linepro.modellbahn.model.SoftDelete;

public interface Item extends SoftDelete {
    Long getId();
    void setId(Long id);
}
