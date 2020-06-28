package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.SteuerungModel;

@Lazy
@Component("SteuerungModelProcessor")
public class SteuerungModelProcessor extends NamedModelProcessor<SteuerungModel> implements RepresentationModelProcessor<SteuerungModel> {

    @Autowired
    public SteuerungModelProcessor() {
        super(
            ApiPaths.ADD_STEUERUNG, 
            ApiPaths.GET_STEUERUNG, 
            ApiPaths.UPDATE_STEUERUNG, 
            ApiPaths.DELETE_STEUERUNG, 
            ApiPaths.SEARCH_STEUERUNG
            );
    }
}
