package com.linepro.modellbahn.entity.impl;

import java.nio.file.Path;

import com.linepro.modellbahn.entity.NamedItem;

public interface NamedWithAbbildungItem extends NamedItem {
    
    Path getAbbildung();
    void setAbbildung(Path abbildung);
}
