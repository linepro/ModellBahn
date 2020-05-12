package com.linepro.modellbahn.hateoas.impl;

import java.util.Set;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.web.util.UriComponentsBuilder;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.LinkBuilder;
import com.linepro.modellbahn.model.NamedItemModel;

public class NamedItemLinkBuilder<M extends NamedItemModel> extends ItemLinkBuilder<M> implements LinkBuilder<M> {

   public NamedItemLinkBuilder(RepositoryRestConfiguration configuration, String requestMapping) {
        super(configuration, requestMapping);
    }

    public Set<Link> getLinks(M model) {
        Set<Link> links = super.getLinks(model);

        String itemPath = UriComponentsBuilder.fromUriString(controllerPath).path(model.getName()).build(true).toUriString();

        links.add(Link.valueOf(itemPath).withRel(ApiNames.SELF));
        links.add(Link.valueOf(itemPath).withRel(ApiNames.UPDATE));
        links.add(Link.valueOf(itemPath).withRel(ApiNames.DELETE));

        return links;
    }
}