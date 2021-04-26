package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_HERSTELLER_ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiRels.ABBILDUNG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.HerstellerModel;

@Lazy
@Component(PREFIX + "HerstellerModelProcessor")
public class HerstellerModelProcessor extends NamedModelProcessor<HerstellerModel> implements RepresentationModelProcessor<HerstellerModel> {

    @Autowired
    public HerstellerModelProcessor() {
        super(
            ADD_HERSTELLER, 
            GET_HERSTELLER, 
            UPDATE_HERSTELLER, 
            DELETE_HERSTELLER, 
            SEARCH_HERSTELLER,
            new LinkTemplateImpl(ABBILDUNG, ADD_HERSTELLER_ABBILDUNG, EXTRACTOR)
            );
    }
}
