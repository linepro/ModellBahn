package com.linepro.modellbahn.model.impl;

import java.net.URI;
import java.net.URISyntaxException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.util.AbstractNamedItem;

@Entity
@Table(name = "HERSTELLER", indexes = { @Index(columnList = "NAME", unique = true) })
public class Hersteller extends AbstractNamedItem implements IHersteller {

    private static final long serialVersionUID = -2896366936132390553L;

    private URI url;

    private String Telefon;

    public Hersteller() {
        super();
    }

    public Hersteller(Long id, String name, String bezeichnung, URI uRL, String telefon, Boolean deleted) {
        super(id, name, bezeichnung, deleted);
    }

    @Transient
    public URI getUrl() {
        return url;
    }

    public void setUrl(URI uRL) {
        url = uRL;
    }

    @Column(name = "URL", nullable = true, length = 512)
    public String getUrlStr() {
        return getUrl() != null ? getUrl().toString() : null;
    }

    public void setUrlStr(String url) {
        try {
            setUrl(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Column(name = "TELEFON", nullable = true, length = 20)
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