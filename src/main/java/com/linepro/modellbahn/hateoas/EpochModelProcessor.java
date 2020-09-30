package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_EPOCH;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_EPOCH;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_EPOCH;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_EPOCH;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_EPOCH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.EpochModel;

@Lazy
@Component(PREFIX + "EpochModelProcessor")
public class EpochModelProcessor extends NamedModelProcessor<EpochModel> implements RepresentationModelProcessor<EpochModel> {

    @Autowired
    public EpochModelProcessor() {
        super(
            ADD_EPOCH,
            GET_EPOCH, 
            UPDATE_EPOCH, 
            DELETE_EPOCH, 
            SEARCH_EPOCH
            );
    }
}
