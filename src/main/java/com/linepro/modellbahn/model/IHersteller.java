package com.linepro.modellbahn.model;

import java.net.URL;

import com.linepro.modellbahn.model.keys.NameKey;

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