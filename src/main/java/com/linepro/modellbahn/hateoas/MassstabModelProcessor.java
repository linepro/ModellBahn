package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.impl.ModelProcessor;
import com.linepro.modellbahn.hateoas.impl.NamedItemLinkBuilder;
import com.linepro.modellbahn.model.MassstabModel;

@Component
public class MassstabModelProcessor extends ModelProcessor<MassstabModel> implements RepresentationModelProcessor<MassstabModel> {

    @Autowired
    public MassstabModelProcessor(RepositoryRestConfiguration configuration) {
        super(new NamedItemLinkBuilder<MassstabModel>(configuration, ApiNames.MASSSTAB));
    }
}
