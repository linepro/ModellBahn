package com.linepro.modellbahn.entity;

import com.linepro.modellbahn.util.SoftDelete;

public interface Item extends SoftDelete {
    Long getId();
    void setId(Long id);
}
