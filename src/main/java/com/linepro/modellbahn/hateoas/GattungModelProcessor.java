package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_GATTUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_GATTUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_GATTUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_GATTUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_GATTUNG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.GattungModel;

@Lazy
@Component(PREFIX + "GattungModelProcessor")
public class GattungModelProcessor extends NamedModelProcessor<GattungModel> implements RepresentationModelProcessor<GattungModel> {

    @Autowired
    public GattungModelProcessor() {
        super(
            ADD_GATTUNG, 
            GET_GATTUNG, 
            UPDATE_GATTUNG, 
            DELETE_GATTUNG, 
            SEARCH_GATTUNG
            );
    }
}
