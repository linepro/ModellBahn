package com.linepro.modellbahn.entity.impl;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * AbstractNamedItem. Base class for items with a name (business key) and a description.
 * @author $Author$
 * @version $Id$
 */
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public class NamedItemImpl extends ItemImpl implements NamedItem {

    /**
     * The name.
     */
    @Column(name = DBNames.NAME, unique = true, length = 50, nullable = false)
    @Pattern(regexp = "^[A-Z0-9\\-.]+$", message = "{com.linepro.modellbahn.validator.constraints.name.invalid}")
    @NotEmpty(message = "{com.linepro.modellbahn.validator.constraints.name.notempty}")
    private String name;

    /**
     * The bezeichnung.
     */
    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.bezeichnung.notnull}")
    private String bezeichnung;
}
