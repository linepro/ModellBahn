package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.HerstellerModel;

@Lazy
@Component
public class HerstellerModelProcessor extends NamedModelProcessor<HerstellerModel> implements RepresentationModelProcessor<HerstellerModel> {

    @Autowired
    public HerstellerModelProcessor() {
        super(ApiPaths.ADD_HERSTELLER, ApiPaths.GET_HERSTELLER, ApiPaths.UPDATE_HERSTELLER, ApiPaths.DELETE_HERSTELLER, ApiPaths.SEARCH_HERSTELLER);
    }
}
