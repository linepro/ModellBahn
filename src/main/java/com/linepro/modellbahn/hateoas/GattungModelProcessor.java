package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.GattungModel;

@Lazy
@Component(PREFIX + "GattungModelProcessor")
public class GattungModelProcessor extends NamedModelProcessor<GattungModel> implements RepresentationModelProcessor<GattungModel> {

    @Autowired
    public GattungModelProcessor() {
        super(
            ApiPaths.ADD_GATTUNG, 
            ApiPaths.GET_GATTUNG, 
            ApiPaths.UPDATE_GATTUNG, 
            ApiPaths.DELETE_GATTUNG, 
            ApiPaths.SEARCH_GATTUNG
            );
    }
}
