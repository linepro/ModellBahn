package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.EpochModel;

@Lazy
@Component
public class EpochModelProcessor extends NamedModelProcessor<EpochModel> implements RepresentationModelProcessor<EpochModel> {

    @Autowired
    public EpochModelProcessor() {
        super(ApiPaths.ADD_EPOCH, ApiPaths.GET_EPOCH, ApiPaths.UPDATE_EPOCH, ApiPaths.DELETE_EPOCH, ApiPaths.SEARCH_EPOCH);
    }
}
