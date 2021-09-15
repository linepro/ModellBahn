package com.linepro.modellbahn.util.impexp;

import javax.servlet.http.HttpServletResponse;

public interface Exporter {

    void write(HttpServletResponse out);
}
