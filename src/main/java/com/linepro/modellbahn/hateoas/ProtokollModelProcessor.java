package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.ProtokollModel;

@Lazy
@Component(PREFIX + "ProtokollModelProcessor")
public class ProtokollModelProcessor extends NamedModelProcessor<ProtokollModel> implements RepresentationModelProcessor<ProtokollModel> {

    @Autowired
    public ProtokollModelProcessor() {
        super(
            ApiPaths.ADD_PROTOKOLL, 
            ApiPaths.GET_PROTOKOLL, 
            ApiPaths.UPDATE_PROTOKOLL, 
            ApiPaths.DELETE_PROTOKOLL, 
            ApiPaths.SEARCH_PROTOKOLL
            );
    }
}
