package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.impl.ModelProcessor;
import com.linepro.modellbahn.hateoas.impl.NamedItemLinkBuilder;
import com.linepro.modellbahn.model.AchsfolgModel;

@Component
public class AchsfolgModelProcessor extends ModelProcessor<AchsfolgModel> implements RepresentationModelProcessor<AchsfolgModel> {

    @Autowired
    public AchsfolgModelProcessor(RepositoryRestConfiguration configuration) {
        super(new NamedItemLinkBuilder<AchsfolgModel>(configuration, ApiNames.ACHSFOLG));
    }
}
