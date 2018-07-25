package com.linepro.modellbahn.rest.util;

import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.GET;
import static javax.ws.rs.HttpMethod.POST;
import static javax.ws.rs.HttpMethod.PUT;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.beanutils.ConvertUtils;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.rest.json.IRepresentation;
import com.linepro.modellbahn.rest.json.Representation;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.util.Selector;
import com.linepro.modellbahn.util.SelectorsBuilder;

/**
 * AbstractService.
 * Basic CRUD rest service 
 * @author $Author$
 * @version $Id$
 * 
 * @param <E> the element type
 */
public abstract class AbstractService<E extends IItem> {

    protected final Logger logger;

    /** The uri info injected by Jersey. */
    @Context
    protected UriInfo uriInfo;

    /** The persister. */
    protected final IPersister<E> persister;

    /** The entity class. */
    protected final Class<E> entityClass;

    /** The selectors. */
    protected final Map<String, Selector> selectors;

    /**
     * Instantiates a new abstract service.
     *
     * @param entityClass the entity class
     * @param persister the persister
     */
    public AbstractService(final Class<E> entityClass, IPersister<E> persister) {
        this.logger = Logger.getLogger("ModellBahn");
        this.persister = persister;
        this.entityClass = entityClass;
        this.selectors = new SelectorsBuilder().build(entityClass, JsonGetter.class);
   }

    /**
     * Adds a new entity.
     *
     * @param entity the entity
     * @return the response
     */
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

    /**
     * Searches for entities (by example using Uri query parameters).
     *
     * @param info the info
     * @return the response
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    public Response search(@Context UriInfo info) {
        try {
            E template = getTemplate(info.getQueryParameters());

            @SuppressWarnings("unchecked")
            List<IItem> entities = (List<IItem>) getPersister().findAll(template);
            
            if (entities.isEmpty()) {
                return getResponse(Response.noContent());
            }

            return getResponse(Response.ok().entity(getRepresentation(entities)));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    /**
     * Createa a template for search / delete from query parameters.
     *
     * @param queryParameters the query parameters
     * @return the template
     * @throws Exception if reflection fails
     */
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

    /**
     * Gets the uri info.
     *
     * @return the uri info
     */
    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    /**
     * Gets the persister.
     *
     * @return the persister
     */
    protected IPersister<E> getPersister() {
        return persister;
    }

    /**
     * Gets the entity class.
     *
     * @return the entity class
     */
    protected Class<E> getEntityClass() {
        return entityClass;
    }

    /**
     * Gets the selectors.
     *
     * @return the selectors
     */
    protected Map<String, Selector> getSelectors() {
        return selectors;
    }

    /**
     * Creates the.
     *
     * @return the e
     * @throws Exception the exception
     */
    protected E create() throws Exception {
        E template = getEntityClass().newInstance();
        return template;
    }

    /**
     * Creates the.
     *
     * @param id the id
     * @return the e
     * @throws Exception the exception
     */
    protected E create(Long id) throws Exception {
        E template = create();
        template.setId(id);
        return template;
    }

    /**
     * Server error.
     *
     * @param e the e
     * @return the response
     */
    protected Response serverError(Exception e) {
        logger.log(Level.SEVERE, "serverError", e);

        return getResponse(Response.serverError());
    }

    /**
     * Gets the representation.
     *
     * @param entity the entity
     * @return the representation
     */
    protected IRepresentation getRepresentation(IItem entity) {
        Representation representation = new Representation(entity);

        addHome(representation);
        addWdl(representation);

        addSelf(entity);
        addParent(entity);

        return representation;
    }

    /**
     * Gets the representation.
     *
     * @param entities the entities
     * @return the representation
     */
    protected IRepresentation getRepresentation(List<IItem> entities) {
        IRepresentation representation = new Representation(entities);

        addHome(representation);
        addWdl(representation);

        for (IItem entity : entities) {
            addSelf(entity);
            addParent(entity);
        }

        return representation;
    }

    /**
     * Make link.
     *
     * @param uri the uri
     * @param rel the rel
     * @param method the method
     * @return the link
     */
    protected Link makeLink(URI uri, String rel, String method) {
        return Link.fromUri(uri).rel(rel).type(method).build();
    }

    /**
     * Make link.
     *
     * @param uri the uri
     * @param path the path
     * @param rel the rel
     * @param method the method
     * @return the link
     */
    protected Link makeLink(URI uri, String path, String rel, String method) {
        return Link.fromUri(UriBuilder.fromUri(uri).path( path).build()).rel(rel).type(method).build();
    }

    /**
     * Adds the home link to a response.
     *
     * @param representation the representation
     */
    protected void addHome(IRepresentation representation) {
        representation.addLink(makeLink(uriInfo.getBaseUri(), "home", GET));
    }
    
    /**
     * Adds the parent link to an item.
     *
     * @param item the item
     */
    protected void addParent(IItem item) {
        item.addLink(makeLink(uriInfo.getAbsolutePath(), "parent", "GET"));
    }

    /**
     * Adds the self link to an item.
     *
     * @param item the item
     */
    protected void addSelf(IItem item) {
        addItemLink(item, "self", GET);
    }

    protected void addAdd(IItem item) {
        addItemLink(item, "add", POST);
    }

    protected void addUpdate(IItem item) {
        addItemLink(item, "update", PUT);
    }

    protected void addDelete(IItem item) {
        addItemLink(item, "delete", DELETE);
    }

    protected void addItemLink(IItem item, String relation, String method) {
        item.addLink(makeLink(uriInfo.getAbsolutePath(), item.getLinkId(), relation, method));
    }

    /**
     * Adds the wdl link to a response.
     *
     * @param representation the representation
     */
    protected void addWdl(IRepresentation representation) {
        representation.addLink(makeLink(uriInfo.getBaseUri(), "application.wadl", "wdl", GET));
    }
    
    /**
     * Gets the response.
     *
     * @param builder the builder
     * @return the response
     */
    protected Response getResponse(ResponseBuilder builder) {
        return builder.build();
    }
}