package com.linepro.modellbahn.model;

import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import com.linepro.modellbahn.hateoas.Hateoas.LinksSchema;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

/**
 * Here because springdoc-hateos doesn't generate the correct model
 * 
 * @author JohnG
 *
 * @param <T>
 */
public class SpringdocModel<T extends RepresentationModel<? extends T>> extends RepresentationModel<T> {

    @Override
    @Schema(name = "_links", description = "HATEOAS Links", implementation = LinksSchema.class, accessMode = AccessMode.READ_ONLY)
    public Links getLinks() {
        return super.getLinks();
    }

}
