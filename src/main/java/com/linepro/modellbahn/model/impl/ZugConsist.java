package com.linepro.modellbahn.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IZug;
import com.linepro.modellbahn.model.IZugConsist;
import com.linepro.modellbahn.model.keys.ZugConsistKey;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.ArtikelResolver;
import com.linepro.modellbahn.rest.json.resolver.ZugResolver;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.util.ToStringBuilder;

/**
 * ZugConsist. A component of a train
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Entity(name = "ZugConsist")
@Table(name = "ZugConsist", indexes = { @Index(columnList = DBNames.ZUG_ID),
        @Index(columnList = DBNames.ARTIKEL_ID, unique = true) }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { DBNames.ZUG_ID, DBNames.POSITION }) })
@JsonRootName(value = ApiNames.CONSIST)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.ZUG, ApiNames.POSITION, ApiNames.ARTIKEL, ApiNames.DELETED, ApiNames.LINKS })
public class ZugConsist extends AbstractItem<ZugConsistKey> implements IZugConsist {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3941436184732408563L;

    /** The zug. */
    @NotNull
    private IZug zug;

    /** The position. */
    @Positive
    private Integer position;

    /** The artikel. */
    @NotNull
    private IArtikel artikel;

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
     *            the deleted
     */
    public ZugConsist(Long id, IZug zug, Integer position, IArtikel artikel, Boolean deleted) {
        super(id, deleted);

        setZug(zug);
        setPosition(position);
        setArtikel(artikel);
    }

    @Override
    @BusinessKey
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Zug.class)
    @JoinColumn(name = DBNames.ZUG_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "consist_fk1"))
    @OrderColumn
    @JsonGetter(ApiNames.ZUG)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver=ZugResolver.class)
    public IZug getZug() {
        return zug;
    }

    @Override
    @JsonSetter(ApiNames.ZUG)
    @JsonDeserialize(as=Zug.class)
    public void setZug(IZug zug) {
        this.zug = zug;
    }

    @Override
    @BusinessKey
    @OrderColumn
    @Column(name = DBNames.POSITION, nullable = false)
    @JsonGetter(ApiNames.POSITION)
    @JsonView(Views.DropDown.class)
    public Integer getPosition() {
        return position;
    }

    @Override
    @JsonSetter(ApiNames.POSITION)
    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Artikel.class)
    @JoinColumn(name = DBNames.ARTIKEL_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = "consist_fk2"))
    @JsonGetter(ApiNames.ARTIKEL)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAME, resolver=ArtikelResolver.class)
    public IArtikel getArtikel() {
        return artikel;
    }

    @Override
    @JsonSetter(ApiNames.ARTIKEL)
    @JsonDeserialize(contentAs = Artikel.class)
    public void setArtikel(IArtikel artikel) {
        this.artikel = artikel;
    }

    @Override
    @Transient
    @JsonIgnore
    public String getParentId() {
        return getZug().getLinkId();
    }

    @Override
    @Transient
    @JsonIgnore
    public String getLinkId() {
        return String.format(ApiPaths.ZUG_CONSIST_LINK, getParentId(), getPosition());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getZug())
                .append(getPosition())
                .hashCode();
    }

    @Override
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("Zug", getZug())
                .append("Position", getPosition())
                .append(ApiNames.ARTIKEL, getArtikel())
                .toString();
    }
}
