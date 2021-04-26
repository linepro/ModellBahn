package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_BAHNVERWALTUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_BAHNVERWALTUNG_ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_BAHNVERWALTUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_BAHNVERWALTUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_BAHNVERWALTUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_BAHNVERWALTUNG;
import static com.linepro.modellbahn.controller.impl.ApiRels.ABBILDUNG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.BahnverwaltungModel;

@Lazy
@Component(PREFIX + "BahnverwaltungModelProcessor")
public class BahnverwaltungModelProcessor extends NamedModelProcessor<BahnverwaltungModel> implements RepresentationModelProcessor<BahnverwaltungModel> {

    @Autowired
    public BahnverwaltungModelProcessor() {
        super(ADD_BAHNVERWALTUNG, GET_BAHNVERWALTUNG, UPDATE_BAHNVERWALTUNG, DELETE_BAHNVERWALTUNG, SEARCH_BAHNVERWALTUNG,
                        new LinkTemplateImpl(ABBILDUNG, ADD_BAHNVERWALTUNG_ABBILDUNG, EXTRACTOR));
    }
}
