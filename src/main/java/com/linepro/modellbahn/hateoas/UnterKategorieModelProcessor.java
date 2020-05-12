package com.linepro.modellbahn.hateoas;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.impl.ModelProcessor;
import com.linepro.modellbahn.hateoas.impl.NamedItemLinkBuilder;
import com.linepro.modellbahn.model.UnterKategorieModel;

@Component
public class UnterKategorieModelProcessor extends ModelProcessor<UnterKategorieModel> implements RepresentationModelProcessor<UnterKategorieModel> {

    @Autowired
    public UnterKategorieModelProcessor(RepositoryRestConfiguration configuration) {
        super(new NamedItemLinkBuilder<UnterKategorieModel>(configuration, ApiNames.KATEGORIE) {

            @Override
            public Set<Link> getLinks(UnterKategorieModel model) {
                // TODO Auto-generated method stub
                return super.getLinks(model);
            }});
    }
}
