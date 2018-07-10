package com.linepro.modellbahn.model;

import java.util.List;

public interface IZug extends INamedItem {

    IZugTyp getTyp();

    void setTyp(IZugTyp typ);

    List<IZugConsist> getConsist();

    void setConsist(List<IZugConsist> consist);

}