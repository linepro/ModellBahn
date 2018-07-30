package com.linepro.modellbahn.rest.util;

import static javax.ws.rs.HttpMethod.GET;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.beanutils.ConvertUtils;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.keys.IdKey;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.persistence.IKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
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
public abstract class AbstractItemService<K extends IKey, E extends IItem<?>> extends AbstractService {

    /** The persister. */
    protected final IPersister<E> persister;

    /** The entity class. */
    protected final Class<E> entityClass;

    /** The selectors. */
    protected final Map<String, Selector> selectors;

    protected Link homeLink;

    protected Link wadlLink;

    protected URI serviceURI;

    /**
     * Instantiates a new abstract service.
     *
     * @param entityClass the entity class
     * @param persister the persister
     */
    public AbstractItemService(final Class<E> entityClass) {
        this.entityClass = entityClass;
        this.persister = StaticPersisterFactory.get().createPersister(entityClass);
        this.selectors = new SelectorsBuilder().build(entityClass, JsonGetter.class);
   }

    @SuppressWarnings("unchecked")
    public Response get(Long id) {
        return get((K) new IdKey(id));
    }

    @SuppressWarnings("unchecked")
    public Response get(String name) {
        return get((K) new NameKey(name));
    }

    /**
     * Gets the.
     *
     * @param id the id
     * @return the response
     */
    public Response get(K key) {
        try {
            E entity = getPersister().findByKey(key, false);

            if (entity == null) {
                return getResponse(notFound());
            }

            return getResponse(ok(), entity, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    /**
     * Adds a new entity.
     *
     * @param entity the entity
     * @return the response
     */
    public Response add(E entity) {
        try {
            info("POST " + entity);

            E result = getPersister().add(entity);

            return getResponse(ok(), result, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @SuppressWarnings("unchecked")
    public Response update(Long id, E entity) {
        return update((K) new IdKey(id), entity);
    }

    @SuppressWarnings("unchecked")
    public Response update(String name, E entity) {
        return update((K) new NameKey(name), entity);
    }

    /**
     * Update.
     *
     * @param id the id
     * @param entity the entity
     * @return the response
     */
    public Response update(K id, E entity) {
        try {
            info("PUT " + id + ": " + entity);

            E result = getPersister().update(id, entity);

            if (result == null) {
                return getResponse(notFound());
            }

            return getResponse(accepted(), result, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @SuppressWarnings("unchecked")
    public Response delete(Long id) {
        return delete((K) new IdKey(id));
    }

    @SuppressWarnings("unchecked")
    public Response delete(String name) {
        return delete((K) new NameKey(name));
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the response
     */
    public Response delete(K id) {
        try {
            info("DELETE " + id);

            getPersister().delete(id);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    /**
     * Searches for entities (by example using Uri query parameters).
     *
     * @param info the info
     * @return the response
     */
    public Response search(UriInfo info) {
        try {
            E template = getTemplate(info.getQueryParameters());

            info("GET " + template);

            @SuppressWarnings("unchecked")
            List<IItem<?>> entities = (List<IItem<?>>) getPersister().findAll(template);
            
            if (entities.isEmpty()) {
                return getResponse(noContent());
            }

            return getResponse(ok(), entities, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
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

    protected Response getResponse(ResponseBuilder builder, IItem<?> entity, boolean update, boolean delete) {
        entity.addLinks(getServiceURI(), update, delete);
        return getResponse(builder.entity(entity));
    }

    protected Response getResponse(ResponseBuilder builder, List<IItem<?>> entities, boolean update, boolean delete) {
        for (IItem<?> entity : entities) {
            entity.addLinks(getServiceURI(), update, delete);
        }

        return getResponse(builder.entity(entities));
    }
    
    /**
     * Gets the response.
     *
     * @param builder the builder
     * @return the response
     */
    protected Response getResponse(ResponseBuilder builder) {
        return builder.links(
                getHomeLink(),
                getWADLLink())
                .build();
    }

    protected URI getServiceURI() {
        if (serviceURI == null) {
            serviceURI = getUriInfo().getBaseUriBuilder().path(getClass()).build();
        }
        
        return serviceURI;
    }

    protected Link getHomeLink() {
        if (homeLink == null) {
            homeLink = Link.fromUri(UriBuilder.fromUri(getUriInfo().getBaseUri()).build()).rel("home").type(GET).build();
        }
        
        return homeLink;
    }

    protected Link getWADLLink() {
        if (wadlLink == null) {
            wadlLink = Link.fromUri(UriBuilder.fromUri(getUriInfo().getBaseUri()).path(ApiPaths.APPLICATION_WADL).build()).rel("wdl").type(GET).build();
        }

        return wadlLink;
    }
}