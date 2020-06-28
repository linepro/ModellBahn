package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.SpurweiteModel;

@Lazy
@Component("SpurweiteModelProcessor")
public class SpurweiteModelProcessor extends NamedModelProcessor<SpurweiteModel> implements RepresentationModelProcessor<SpurweiteModel> {

    @Autowired
    public SpurweiteModelProcessor() {
        super(
            ApiPaths.ADD_SPURWEITE, 
            ApiPaths.GET_SPURWEITE, 
            ApiPaths.UPDATE_SPURWEITE, 
            ApiPaths.DELETE_SPURWEITE, 
            ApiPaths.SEARCH_SPURWEITE
            );
    }
}
