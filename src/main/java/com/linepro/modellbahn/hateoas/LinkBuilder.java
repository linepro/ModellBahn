package com.linepro.modellbahn.hateoas;

import java.util.Set;

import org.springframework.hateoas.Link;

public interface LinkBuilder<M> {
    Set<Link> getLinks(M model);
}