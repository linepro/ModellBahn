package com.linepro.modellbahn.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.linepro.modellbahn.persistence.DBNames;

public interface Named extends SoftDelete {
    @Pattern(regexp = "^" + DBNames.NAME_PATTERN + "$", message = "{com.linepro.modellbahn.validator.constraints.name.invalid}")
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.name.notempty}")
    String getName();
    void setName(String name);

    @Size(max = 100, message = "{com.linepro.modellbahn.validator.constraints.maxLength}")
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notnull}")
    String getBezeichnung();
    void setBezeichnung(String bezeichnung);
}

