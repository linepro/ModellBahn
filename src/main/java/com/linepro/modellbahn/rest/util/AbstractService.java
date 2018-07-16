package com.linepro.modellbahn.rest.util;

import static javax.ws.rs.HttpMethod.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.beanutils.ConvertUtils;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.rest.json.IRepresentation;
import com.linepro.modellbahn.rest.json.ItemRepresentation;
import com.linepro.modellbahn.rest.json.ItemsRepresentation;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.Selector;
import com.linepro.modellbahn.util.SelectorsBuilder;

public abstract class AbstractService<E extends IItem> {

    @Context
    protected UriInfo uriInfo;

    protected final IPersister<E> persister;

    protected final Class<E> entityClass;

    protected final Map<String, Selector> selectors;

    public AbstractService(final Class<E> entityClass, IPersister<E> persister) {
        this.persister = persister;
        this.entityClass = entityClass;
        this.selectors = new SelectorsBuilder().build(entityClass, Arrays.asList(JsonGetter.class));
   }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response add(E entity) {
        try {
            E result = getPersister().add(entity);

            return getResponse(Response.ok().entity(getRepresentation(result)));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    public Response search(@Context UriInfo info) {
        try {
            E template = getTemplate(info.getQueryParameters());

            List<E> entities = getPersister().findAll(template);
            
            if (entities.isEmpty()) {
                return getResponse(Response.noContent());
            }

            return getResponse(Response.ok().entity(getRepresentation(entities)));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    protected E getTemplate(MultivaluedMap<String, String> queryParameters) throws Exception {
        E template = create();

        if (!queryParameters.isEmpty()) {
            for (String name : queryParameters.keySet()) {
                Object value = queryParameters.getFirst(name);

                Selector selector = selectors.get(name);

                if (selector != null) {
                    selector.getSetter().invoke(template,
                            ConvertUtils.convert(value, selector.getGetter().getReturnType()));
                }
            }
        }

        return template;
    }

    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    protected IPersister<E> getPersister() {
        return persister;
    }

    protected Class<E> getEntityClass() {
        return entityClass;
    }

    protected Map<String, Selector> getSelectors() {
        return selectors;
    }

    protected E create() throws Exception {
        E template = getEntityClass().newInstance();
        return template;
    }

    protected E create(Long id) throws Exception {
        E template = create();
        template.setId(id);
        return template;
    }

    protected Response serverError(Exception e) {
        return getResponse(Response.serverError());
    }

    protected IRepresentation<E> getRepresentation(E entity) {
        return getRepresentation(entity, true);
    }

    protected IRepresentation<E> getRepresentation(E entity, boolean root) {
        ItemRepresentation<E> representation = new ItemRepresentation<>(entity);

        addSelf(representation);
        
        if (root) {
            addHome(representation);
            addParent(representation);
            addWdl(representation);
        }
        
        return representation;
    }

    protected IRepresentation<List<IRepresentation<?>>> getRepresentation(List<E> entities) {
        List<IRepresentation<?>> representations = new ArrayList<>(entities.size());

        for (E entity : entities) {
            representations.add(getRepresentation(entity, false));
        }

        IRepresentation<List<IRepresentation<?>>> representation = new ItemsRepresentation<>(representations);

        addHome(representation);
        addParent(representation);
        addWdl(representation);
        
        return representation;
    }

    protected Link makeLink(URI uri, String rel, String method) {
        return Link.fromUri(uri).rel(rel).type(method).build();
    }

    protected Link makeLink(URI uri, String path, String rel, String method) {
        return Link.fromUri(uri + path).rel(rel).type(method).build();
    }

    protected void addHome(IRepresentation<?> representation) {
        representation.addLink(makeLink(uriInfo.getBaseUri(), "home", GET));
    }
    
    protected void addParent(IRepresentation<?> representation) {
        representation.addLink(makeLink(uriInfo.getAbsolutePath(), "parent", "GET"));
    }

    protected void addSelf(IRepresentation<E> representation) {
        representation.addLink(makeLink(uriInfo.getAbsolutePath(), representation.getItem().getId().toString(), "self", GET));
    }

    protected void addWdl(IRepresentation<?> representation) {
        representation.addLink(makeLink(uriInfo.getBaseUri(), "application.wadl", "wdl", GET));
    }
    
    protected Response getResponse(ResponseBuilder builder) {
        return builder.build();
    }
}