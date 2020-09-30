package com.linepro.modellbahn.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Unique;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * UnterKategorie.
 * The sub category for a product
 * @author  $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.UNTER_KATEGORIE)
@Table(name = DBNames.UNTER_KATEGORIE,
    indexes = { 
        @Index(name = DBNames.UNTER_KATEGORIE + "_IX1", columnList = DBNames.KATEGORIE_ID)
    }, uniqueConstraints = {
        @UniqueConstraint(name = DBNames.UNTER_KATEGORIE + "_UC1", columnNames = { DBNames.KATEGORIE_ID, DBNames.NAME })
    })
@NamedEntityGraphs({
    @NamedEntityGraph(name="unterKategorie",
        includeAllAttributes = true,
        attributeNodes = {
            @NamedAttributeNode(value = "kategorie", subgraph = "unterKategorie.kategorie")
        }, subgraphs = {
            @NamedSubgraph(name = "unterKategorie.kategorie",
                attributeNodes = {
                    @NamedAttributeNode(value = "id"),
                    @NamedAttributeNode(value = "name")
            })
        })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@AttributeOverride(name = DBNames.NAME, column = @Column(name = DBNames.NAME, length = 50))
@Unique(message = "{com.linepro.modellbahn.validator.constraints.unterKategorie.notunique}")
public class UnterKategorie extends NamedItemImpl {

    /** The kategorie. */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Kategorie.class, optional = false)
    @JoinColumn(name = DBNames.KATEGORIE_ID, nullable = false, referencedColumnName = DBNames.ID, foreignKey = @ForeignKey(name = DBNames.UNTER_KATEGORIE + "_fk1"))
    @NotNull(message = "{com.linepro.modellbahn.validator.constraints.kategorie.notnull}")
    private Kategorie kategorie;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("kategorie", kategorie.getName())
            .toString();
    }
}