package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public interface IUrl {
    @JsonGetter
    String getUrl();
}
