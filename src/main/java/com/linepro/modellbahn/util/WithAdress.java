package com.linepro.modellbahn.util;

import com.linepro.modellbahn.model.enums.AdressTyp;

public interface WithAdress {
    AdressTyp getAdressTyp();
    Integer getAdress();
}
