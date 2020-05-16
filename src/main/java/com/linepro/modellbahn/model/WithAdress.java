package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.enums.AdressTyp;

public interface WithAdress {
    AdressTyp getAdressTyp();
    Integer getAdress();
}
