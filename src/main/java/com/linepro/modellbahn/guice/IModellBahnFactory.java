package com.linepro.modellbahn.guice;

import java.net.URI;

import com.linepro.modellbahn.ModellBahn;

public interface IModellBahnFactory {

    ModellBahn create(URI baseUri);
}
