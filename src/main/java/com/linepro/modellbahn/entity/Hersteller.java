package com.linepro.modellbahn.entity;

import java.net.URL;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.linepro.modellbahn.entity.impl.NamedItemImpl;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.URLConverter;
import com.linepro.modellbahn.validation.Country;
import com.linepro.modellbahn.validation.Unique;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
@ToString(callSuper = true)
@Cacheable
@Unique
public class Hersteller extends NamedItemImpl {

    /** The url. */
    //@org.hibernate.validator.constraints.URL
    @Column(name = DBNames.URL)
    @Convert(converter = URLConverter.class)
    private URL url;

    /** The Telefon. */
    //@Telephone(message = "{com.linepro.modellbahn.validator.constraints.land.invalid}")
    @Column(name = DBNames.TELEFON, length = 20)
    private String Telefon;

    @Column(name = DBNames.LAND, length = 2)
    @Country(message = "{com.linepro.modellbahn.validator.constraints.land.invalid}")
    private String land;
}