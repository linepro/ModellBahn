package com.linepro.modellbahn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * ProduktTeil.
 * Part of a product (Bill of materials).
 * E.g. Locomotives and Carriages in a set
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.PRODUKT_TEIL)
@Table(name = DBNames.PRODUKT_TEIL,
    indexes = { 
        @Index(name = DBNames.PRODUKT_TEIL + "_IX1", columnList = DBNames.PRODUKT_ID),
        @Index(name = DBNames.PRODUKT_TEIL + "_IX2", columnList = DBNames.TEIL_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.PRODUKT_TEIL + "_UC1", columnNames = { DBNames.PRODUKT_ID, DBNames.TEIL_ID })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProduktTeil extends ItemImpl {
    
    /** The produkt. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = DBNames.PRODUKT_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT_TEIL + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.produkt.notnull}")
    private Produkt produkt;

    /** The component produkt */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produkt.class)
    @JoinColumn(name = DBNames.TEIL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.PRODUKT_TEIL + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.teil.notnull}")
    private Produkt teil;
    
    /** The count of this component. */
    @Column(name = DBNames.ANZAHL, nullable = false)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.anzahl.positive}")
    private Integer anzahl;
}
