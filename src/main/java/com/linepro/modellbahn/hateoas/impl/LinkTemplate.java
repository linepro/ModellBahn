package com.linepro.modellbahn.hateoas.impl;

import java.util.Map;

import org.springframework.hateoas.RepresentationModel;

public interface LinkTemplate {

    String getRel();
    
    String getPath();
    
    void apply(RepresentationModel<?> model, Map<String,Object> paths);
}
