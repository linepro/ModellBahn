package com.linepro.modellbahn.hateoas.base;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.AchsfolgController;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.model.AchsfolgModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AchsfolgProcessor implements RepresentationModelProcessor<AchsfolgModel> {

    private final String basePath;

    @Autowired
    public AchsfolgProcessor(RepositoryRestConfiguration configuration) {
        basePath = configuration.getBasePath().toString();
    }

    @Override
    public AchsfolgModel process(AchsfolgModel model) {
        String name = model.getName();

        AchsfolgController controller = methodOn(AchsfolgController.class);

        addLink(model, linkTo(controller.get(name)), ApiNames.SELF);
        addLink(model, linkTo(controller.update(name, null)), ApiNames.UPDATE);
        addLink(model, linkTo(controller.delete(name)), ApiNames.DELETE);
        addLink(model, linkTo(controller.delete(name)), ApiNames.API);
        addLink(model, linkTo(controller.delete(name)), ApiNames.HOME);
        addLink(model, linkTo(controller.delete(name)), ApiNames.WADL);

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
