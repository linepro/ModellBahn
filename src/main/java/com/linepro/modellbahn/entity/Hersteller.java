package com.linepro.modellbahn.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.URL;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.util.ToStringBuilder;
import com.linepro.modellbahn.validation.Country;
import com.linepro.modellbahn.validation.Telefon;
import com.linepro.modellbahn.validation.Unique;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Hersteller. The manufacturer for a product
 * 
 * @author $Author:$
 * @version $Id:$
 */
//@formatter:off
@Entity(name = DBNames.HERSTELLER)
@Table(name = DBNames.HERSTELLER,
    uniqueConstraints = {
        @UniqueConstraint(name = DBNames.HERSTELLER + "_UC1", columnNames = { DBNames.NAME })
    })
//@formatter:on
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@Unique(message = "{com.linepro.modellbahn.validator.constraints.hersteller.notunique}")
@Telefon(message = "{com.linepro.modellbahn.validator.constraints.telephone.invalid}")
public class Hersteller extends NamedItemImpl implements HasTelefon {

    /** The url. */
    @URL(message = "{com.linepro.modellbahn.validator.constraints.url.invalid}")
    @Column(name = DBNames.URL)
    private String url;

    /** The Telefon. */
    @Column(name = DBNames.TELEFON, length = 20)
    private String telefon;

    @Column(name = DBNames.LAND, length = 2)
    @Country(message = "{com.linepro.modellbahn.validator.constraints.land.invalid}")
    private String land;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("url", url)
                .append("telefon", telefon)
                .append("land", land)
                .toString();
    }
}