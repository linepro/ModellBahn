package com.linepro.modellbahn.hateoas.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.web.util.UriComponentsBuilder;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.LinkBuilder;
import com.linepro.modellbahn.model.ItemModel;

public class ItemLinkBuilder<M extends ItemModel> implements LinkBuilder<M> {

    protected final String basePath;

    protected final String controllerPath;

    public ItemLinkBuilder(RepositoryRestConfiguration configuration, String requestMapping) {
        basePath = configuration.getBasePath().toString();
        this.controllerPath = UriComponentsBuilder.fromUriString(basePath).path(requestMapping).build().toUriString();
    }

    public Set<Link> getLinks(M model) {
        Set<Link> links = new HashSet<>();

        links.add(Link.valueOf(basePath).withRel(ApiNames.HOME));
        links.add(Link.valueOf(controllerPath).withRel(ApiNames.PARENT));
        links.add(Link.valueOf(controllerPath).withRel(ApiNames.ADD));

        return links;
    }
}
