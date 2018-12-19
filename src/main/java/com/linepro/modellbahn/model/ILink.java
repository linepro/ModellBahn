package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public interface ILink {
    @JsonGetter
    String getHRef();

    @JsonGetter
    String getRel();

    @JsonGetter
    String getMethod();
}
