package com.linepro.modellbahn.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.controller.base.NamedItemController;
import com.linepro.modellbahn.model.base.NamedItemModelImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public abstract class NamedItemModelProcessor<M extends NamedItemModelImpl<M>> implements RepresentationModelProcessor<M> {

    @Autowired
    @Qualifier("BaseURL")
    private String basePath;

    private final Class<?> controllerClass;
    
    public NamedItemModelProcessor(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }

    @Override
    public M process(M model) {
        String name = model.getName();

        NamedItemController<?> controller = (NamedItemController<?>) methodOn(controllerClass);

        addLink(model, linkTo(controller.get(name)), ApiNames.SELF);
        addLink(model, linkTo(controller.update(name, null)), ApiNames.UPDATE);
        addLink(model, linkTo(controller.delete(name)), ApiNames.DELETE);

        return model;
    }

    protected void addLink(RepresentationModel<?> model, WebMvcLinkBuilder builder, String rel) {
        URI uri = builder.toUri();

        try {
            uri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), basePath + uri.getPath(),
                    uri.getQuery(), uri.getFragment());
        } catch (URISyntaxException e) {
            log.error("Cannot adjust link {}: {}", uri, e.getMessage());
        }

        model.add(new Link(uri.toString(), rel));
    }
}
