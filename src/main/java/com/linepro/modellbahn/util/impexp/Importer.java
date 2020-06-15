package com.linepro.modellbahn.util.impexp;

import java.io.Reader;

public interface Importer {

    void read(Reader in) throws Exception;
}
