package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.impl.ModelProcessor;
import com.linepro.modellbahn.hateoas.impl.NamedItemLinkBuilder;
import com.linepro.modellbahn.model.AntriebModel;

@Component
public class AntriebModelProcessor extends ModelProcessor<AntriebModel> implements RepresentationModelProcessor<AntriebModel> {

    @Autowired
    public AntriebModelProcessor(RepositoryRestConfiguration configuration) {
        super(new NamedItemLinkBuilder<AntriebModel>(configuration, ApiNames.ANTRIEB));
    }
}
