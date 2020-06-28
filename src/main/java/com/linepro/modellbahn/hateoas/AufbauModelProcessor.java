package com.linepro.modellbahn.hateoas;

import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.AufbauModel;

@Lazy
@Component("AufbauModelProcessor")
public class AufbauModelProcessor extends NamedModelProcessor<AufbauModel> implements RepresentationModelProcessor<AufbauModel> {

    public AufbauModelProcessor() {
        super(
            ApiPaths.ADD_AUFBAU,
            ApiPaths.GET_AUFBAU,
            ApiPaths.UPDATE_AUFBAU,
            ApiPaths.DELETE_AUFBAU,
            ApiPaths.SEARCH_AUFBAU,
            new LinkTemplateImpl(ApiRels.IMAGE, ApiPaths.ADD_AUFBAU_ABBILDUNG, EXTRACTOR)
            );
    }
}
