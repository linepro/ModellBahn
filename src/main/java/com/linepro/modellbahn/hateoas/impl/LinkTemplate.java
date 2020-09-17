package com.linepro.modellbahn.hateoas.impl;

import org.springframework.hateoas.RepresentationModel;

public interface LinkTemplate {

    String getRel();

    String getPath();

    void apply(RepresentationModel<?> model);
}
