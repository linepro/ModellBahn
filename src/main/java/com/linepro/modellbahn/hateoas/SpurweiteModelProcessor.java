package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_SPURWEITE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_SPURWEITE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_SPURWEITE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_SPURWEITE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_SPURWEITE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.SpurweiteModel;

@Lazy
@Component(PREFIX + "SpurweiteModelProcessor")
public class SpurweiteModelProcessor extends NamedModelProcessor<SpurweiteModel> implements RepresentationModelProcessor<SpurweiteModel> {

    @Autowired
    public SpurweiteModelProcessor() {
        super(
            ADD_SPURWEITE, 
            GET_SPURWEITE, 
            UPDATE_SPURWEITE, 
            DELETE_SPURWEITE, 
            SEARCH_SPURWEITE
            );
    }
}
