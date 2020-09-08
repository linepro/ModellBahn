package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_AUFBAU;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_AUFBAU_ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_AUFBAU;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_AUFBAU;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_AUFBAU;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_AUFBAU;
import static com.linepro.modellbahn.controller.impl.ApiRels.ABBILDUNG;

import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.AufbauModel;

@Lazy
@Component(PREFIX + "AufbauModelProcessor")
public class AufbauModelProcessor extends NamedModelProcessor<AufbauModel> implements RepresentationModelProcessor<AufbauModel> {

    public AufbauModelProcessor() {
        super(
            ADD_AUFBAU,
            GET_AUFBAU,
            UPDATE_AUFBAU,
            DELETE_AUFBAU,
            SEARCH_AUFBAU,
            new LinkTemplateImpl(ABBILDUNG, ADD_AUFBAU_ABBILDUNG, EXTRACTOR)
            );
    }
}
