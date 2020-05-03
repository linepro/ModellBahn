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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.base.Item;
import com.linepro.modellbahn.entity.base.ItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * ZugConsist. A component of a train
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = DBNames.ZUG_CONSIST)
@Table(name = DBNames.ZUG_CONSIST,
        indexes = {
                @Index(columnList = DBNames.ZUG_ID),
                @Index(columnList = DBNames.ARTIKEL_ID)
        }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.ZUG_ID, DBNames.POSITION })
        })
public class ZugConsist extends ItemImpl {

    /** The zug. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.zug.notnull}")
    private Zug zug;

    /** The position. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.position.notnull}")
    @Min(value=1, message = "{com.linepro.modellbahn.validator.constraints.position.positive}")
    private Integer position;

    /** The artikel. */
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.artikel.notnull}")
    private Artikel artikel;

    /**
     * Instantiates a new zug consist.
     */
    public ZugConsist() {
        super();
    }

    /**
     * Instantiates a new zug consist.
     *
     * @param id
     *            the id
     * @param zug
     *            the zug
     * @param position
     *            the position
     * @param artikel
     *            the artikel
     * @param deleted
     *            if  { this item is soft deleted, otherwise it is active
     */
    public ZugConsist(Long id, Zug zug, Integer position, Artikel artikel, Boolean deleted) {
        super(id, deleted);

        setZug(zug);
        setPosition(position);
        setArtikel(artikel);
    }

    
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Zug.class)
    @JoinColumn(name = DBNames.ZUG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ZUG_CONSIST + "_fk1"))
    public Zug getZug() {
        return zug;
    }

    
    public void setZug(Zug zug) {
        this.zug = zug;
    }

    
    @BusinessKey
    @Column(name = DBNames.POSITION, nullable = false)
    public Integer getPosition() {
        return position;
    }

    
    public void setPosition(Integer position) {
        this.position = position;
    }

    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Artikel.class)
    @JoinColumn(name = DBNames.ARTIKEL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.ZUG_CONSIST + "_fk2"))
    public Artikel getArtikel() {
        return artikel;
    }

    
    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    @Override
    public int compareTo(Item other) {
        if (other instanceof ZugConsist) {
            return new CompareToBuilder()
                    .append(getZug(), ((ZugConsist) other).getZug())
                    .append(getPosition(), ((ZugConsist) other).getPosition())
                    .toComparison();
        }
        
        return super.compareTo(other);
    }

    
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getZug())
                .append(getPosition())
                .hashCode();
    }

    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ZugConsist)) {
            return false;
        }

        ZugConsist other = (ZugConsist) obj;

        return new EqualsBuilder()
                .append(getZug(), other.getZug())
                .append(getPosition(), other.getPosition())
                .isEquals();
    }

    
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ApiNames.ZUG, getZug())
                .append(ApiNames.POSITION, getPosition())
                .append(ApiNames.ARTIKEL, getArtikel())
                .toString();
    }
}
