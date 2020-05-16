package com.linepro.modellbahn.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Artikel.
 * An article.
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.ANDERUNG)
@Table(name = DBNames.ANDERUNG,
    indexes = {
        @Index(name = DBNames.ANDERUNG + "_IX1", columnList = DBNames.ARTIKEL_ID),
        @Index(name = DBNames.ANDERUNG + "_IX2", columnList = DBNames.ANDERUNG_ID)
    },uniqueConstraints = {
        @UniqueConstraint(name = DBNames.ANDERUNG + "_UC1", columnNames = {DBNames.ARTIKEL_ID, DBNames.ANDERUNG_ID})
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Anderung extends ItemImpl {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Artikel.class)
    @JoinColumn(name = DBNames.ARTIKEL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ANDERUNG + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.artikel.notnull}")
    private Artikel artikel;

    @Column(name = DBNames.ANDERUNG_ID, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.anderungId.notnull}")
    @Min(value = 1, message = "{com.linepro.modellbahn.validator.constraints.anderungId.positive}")
    private Integer anderungId;

    @Column(name = DBNames.ANDERUNGSDATUM)
    @Past(message = "{com.linepro.modellbahn.validator.constraints.anderungsDatum.past}")
    private LocalDate anderungsDatum;

    @Column(name = DBNames.ANDERUNGS_TYP, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.anderungsTyp.notnull}")
    private AnderungsTyp anderungsTyp;

    @Column(name = DBNames.BEZEICHNUNG, length = 100)
    private String bezeichnung;

    @Column(name = DBNames.STUCK)
    @Positive(message = "{com.linepro.modellbahn.validator.constraints.stuck.positive}")
    private Integer stuck;

    @Column(name = DBNames.ANMERKUNG)
    private String anmerkung;
}
