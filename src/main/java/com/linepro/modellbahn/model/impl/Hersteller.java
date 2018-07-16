package com.linepro.modellbahn.model.impl;

import java.net.URI;
import java.net.URISyntaxException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "hersteller", indexes = { @Index(columnList = "name", unique = true) }, 
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Hersteller extends AbstractNamedItem implements IHersteller {

    private static final long serialVersionUID = -2896366936132390553L;

    private URI url;

    private String Telefon;

    public Hersteller() {
        super();
    }

    public Hersteller(Long id, String name, String bezeichnung, URI url, String telefon, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
        
        setUrl(url);
        setTelefon(telefon);
    }

    @Transient
    public URI getUrl() {
        return url;
    }

    public void setUrl(URI uRL) {
        url = uRL;
    }

    @Column(name = "url", nullable = true, length = 512)
    public String getUrlStr() {
        return getUrl() != null ? getUrl().toString() : null;
    }

    public void setUrlStr(String url) {
        try {
            setUrl(url != null ? new URI(url) : null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Column(name = "telefon", nullable = true, length = 20)
    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString()).append("url", getUrl())
                .append("telefon", getTelefon()).toString();
    }
}