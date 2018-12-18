package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.keys.NameKey;

import java.net.URL;

/**
 * IHersteller.
 * @author   $Author$
 * @version  $Id$
 */
public interface IHersteller extends INamedItem<NameKey> {

    URL getUrl();

    void setUrl(URL url);

    String getTelefon();

    void setTelefon(String telefon);

}