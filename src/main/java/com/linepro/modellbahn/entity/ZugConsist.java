package com.linepro.modellbahn.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.linepro.modellbahn.entity.impl.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * ZugConsist. A component of a train
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.ZUG_CONSIST)
@Table(name = DBNames.ZUG_CONSIST,
    indexes = {
        @Index(name = DBNames.ZUG_CONSIST + "_IX1", columnList = DBNames.ZUG_ID),
        @Index(name = DBNames.ZUG_CONSIST + "_IX2", columnList = DBNames.ARTIKEL_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.ZUG_CONSIST + "_UC1", columnNames = { DBNames.ZUG_ID, DBNames.POSITION })
        })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Cacheable
public class ZugConsist extends ItemImpl {

    /** The zug. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Zug.class)
    @JoinColumn(name = DBNames.ZUG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ZUG_CONSIST + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.zug.notnull}")
    private Zug zug;

    /** The position. */
    @Column(name = DBNames.POSITION, nullable = false)
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.position.notnull}")
    @Min(value=1, message = "{com.linepro.modellbahn.validator.constraints.position.positive}")
    private Integer position;

    /** The artikel. */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Artikel.class)
    @JoinColumn(name = DBNames.ARTIKEL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ZUG_CONSIST + "_fk2"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.artikel.notnull}")
    private Artikel artikel;
}
