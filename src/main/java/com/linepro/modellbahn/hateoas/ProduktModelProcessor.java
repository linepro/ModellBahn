package com.linepro.modellbahn.hateoas;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.impl.ItemLinkBuilder;
import com.linepro.modellbahn.hateoas.impl.ModelProcessor;
import com.linepro.modellbahn.model.ProduktModel;

@Component
public class ProduktModelProcessor extends ModelProcessor<ProduktModel> implements RepresentationModelProcessor<ProduktModel> {

    @Autowired
    public ProduktModelProcessor(RepositoryRestConfiguration configuration) {
        super(new ItemLinkBuilder<ProduktModel>(configuration, ApiNames.PRODUKT) {
            @Override
            public Set<Link> getLinks(ProduktModel model) {
                return super.getLinks(model);
            }});
    }
}
