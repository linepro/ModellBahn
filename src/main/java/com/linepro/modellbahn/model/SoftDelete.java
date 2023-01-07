package com.linepro.modellbahn.model;

import javax.validation.constraints.NotNull;

public interface SoftDelete {
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.deleted.notnull}")
    Boolean getDeleted();
    void setDeleted(Boolean deleted);
}
