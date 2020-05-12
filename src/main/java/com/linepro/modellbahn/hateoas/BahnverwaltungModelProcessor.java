package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.impl.ModelProcessor;
import com.linepro.modellbahn.hateoas.impl.NamedItemLinkBuilder;
import com.linepro.modellbahn.model.BahnverwaltungModel;

@Component
public class BahnverwaltungModelProcessor extends ModelProcessor<BahnverwaltungModel> implements RepresentationModelProcessor<BahnverwaltungModel> {

    @Autowired
    public BahnverwaltungModelProcessor(RepositoryRestConfiguration configuration) {
        super(new NamedItemLinkBuilder<BahnverwaltungModel>(configuration, ApiNames.BAHNVERWALTUNG));
    }
}
