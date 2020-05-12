package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.impl.ItemLinkBuilder;
import com.linepro.modellbahn.hateoas.impl.ModelProcessor;
import com.linepro.modellbahn.model.AnderungModel;

@Component
public class AnderungModelProcessor extends ModelProcessor<AnderungModel> implements RepresentationModelProcessor<AnderungModel> {

    @Autowired
    public AnderungModelProcessor(RepositoryRestConfiguration configuration) {
        super(new ItemLinkBuilder<AnderungModel>(configuration, ApiNames.ARTIKEL));
    }
}
