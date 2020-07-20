package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_STEUERUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_STEUERUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_STEUERUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_STEUERUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_STEUERUNG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.SteuerungModel;

@Lazy
@Component(PREFIX + "SteuerungModelProcessor")
public class SteuerungModelProcessor extends NamedModelProcessor<SteuerungModel> implements RepresentationModelProcessor<SteuerungModel> {

    @Autowired
    public SteuerungModelProcessor() {
        super(
            ADD_STEUERUNG, 
            GET_STEUERUNG, 
            UPDATE_STEUERUNG, 
            DELETE_STEUERUNG, 
            SEARCH_STEUERUNG
            );
    }
}
