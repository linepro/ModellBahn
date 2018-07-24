package com.linepro.modellbahn.model;

import java.net.URL;

/**
 * IHersteller.
 * @author   $Author$
 * @version  $Id$
 */
public interface IHersteller extends INamedItem {

    URL getUrl();

    void setUrl(URL url);

    String getTelefon();

    void setTelefon(String telefon);

}