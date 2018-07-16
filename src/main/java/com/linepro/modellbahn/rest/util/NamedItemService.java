package com.linepro.modellbahn.rest.util;

import static javax.ws.rs.HttpMethod.GET;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.persistence.INamedItemPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.IRepresentation;
import com.linepro.modellbahn.rest.json.Views;

public abstract class NamedItemService<E extends INamedItem> extends AbstractService<E> {

    public NamedItemService(final Class<E> entityClass) {
        super(entityClass, (INamedItemPersister<E>) StaticPersisterFactory.get().createNamedItemPersister(entityClass));
    }

    @JsonCreator
    public E create(@JsonProperty(value="id", required=false) Long id, 
                    @JsonProperty(value="name", required=false) String name, 
                    @JsonProperty(value="description", required=false) String bezeichnung, 
                    @JsonProperty(value="deleted", required=false) Boolean deleted) throws Exception {
        E entity = create();

        entity.setId(id);
        entity.setName(name);
        entity.setBezeichnung(bezeichnung);
        entity.setDeleted(deleted);

        return entity;
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response get(@PathParam("name") String name) {
        try {
            E entity = ((INamedItemPersister<E>) getPersister()).findByName(name);

            if (entity == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            return getResponse(Response.ok().entity(getRepresentation(entity)));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @PUT
    @Path("/{name}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam("name") String name, E entity) {
        try {
            entity.setName(name);

            E result = getPersister().update(entity);

            return getResponse(Response.accepted().entity(getRepresentation(result)));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam("name") String name) {
        try {
            getPersister().delete(create(name));

            return getResponse(Response.noContent());
        } catch (Exception e) {
            return serverError(e);
        }
    }

    protected E create(String name) throws Exception {
        E template = getEntityClass().newInstance();
        template.setName(name);
        return template;
    }

    @Override
    protected void addSelf(IRepresentation<E> representation) {
        representation.addLink(makeLink(uriInfo.getAbsolutePath(), representation.getItem().getName(), "self", GET));
    }

}