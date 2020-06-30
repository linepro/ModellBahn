package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.BahnverwaltungModel;

@Lazy
@Component(PREFIX + "BahnverwaltungModelProcessor")
public class BahnverwaltungModelProcessor extends NamedModelProcessor<BahnverwaltungModel> implements RepresentationModelProcessor<BahnverwaltungModel> {

    @Autowired
    public BahnverwaltungModelProcessor() {
        super(
            ApiPaths.ADD_BAHNVERWALTUNG,
            ApiPaths.GET_BAHNVERWALTUNG,
            ApiPaths.UPDATE_BAHNVERWALTUNG,
            ApiPaths.DELETE_BAHNVERWALTUNG,
            ApiPaths.SEARCH_BAHNVERWALTUNG
            );
    }
}
