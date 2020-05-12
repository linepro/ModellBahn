package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.impl.ModelProcessor;
import com.linepro.modellbahn.hateoas.impl.NamedItemLinkBuilder;
import com.linepro.modellbahn.model.SonderModellModel;

@Component
public class SonderModellModelProcessor extends ModelProcessor<SonderModellModel> implements RepresentationModelProcessor<SonderModellModel> {

    @Autowired
    public SonderModellModelProcessor(RepositoryRestConfiguration configuration) {
        super(new NamedItemLinkBuilder<SonderModellModel>(configuration, ApiNames.SONDERMODELL));
    }
}
