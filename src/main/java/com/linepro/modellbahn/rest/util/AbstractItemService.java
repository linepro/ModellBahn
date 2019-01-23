package com.linepro.modellbahn.rest.util;

import static javax.ws.rs.HttpMethod.GET;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Link.Builder;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.IAnderung;
import com.linepro.modellbahn.model.IAntrieb;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypAdress;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.IEpoch;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.IMassstab;
import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.ISonderModell;
import com.linepro.modellbahn.model.ISpurweite;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.IZug;
import com.linepro.modellbahn.model.IZugConsist;
import com.linepro.modellbahn.model.IZugTyp;
import com.linepro.modellbahn.model.impl.Achsfolg;
import com.linepro.modellbahn.model.impl.Anderung;
import com.linepro.modellbahn.model.impl.Antrieb;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.model.impl.Aufbau;
import com.linepro.modellbahn.model.impl.Bahnverwaltung;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.impl.DecoderTypAdress;
import com.linepro.modellbahn.model.impl.DecoderTypCV;
import com.linepro.modellbahn.model.impl.DecoderTypFunktion;
import com.linepro.modellbahn.model.impl.Epoch;
import com.linepro.modellbahn.model.impl.Gattung;
import com.linepro.modellbahn.model.impl.Hersteller;
import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.model.impl.Kupplung;
import com.linepro.modellbahn.model.impl.Licht;
import com.linepro.modellbahn.model.impl.Massstab;
import com.linepro.modellbahn.model.impl.MotorTyp;
import com.linepro.modellbahn.model.impl.Produkt;
import com.linepro.modellbahn.model.impl.ProduktTeil;
import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.model.impl.SonderModell;
import com.linepro.modellbahn.model.impl.Spurweite;
import com.linepro.modellbahn.model.impl.Steuerung;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.model.impl.Vorbild;
import com.linepro.modellbahn.model.impl.Wahrung;
import com.linepro.modellbahn.model.impl.Zug;
import com.linepro.modellbahn.model.impl.ZugConsist;
import com.linepro.modellbahn.model.impl.ZugTyp;
import com.linepro.modellbahn.model.keys.AnderungKey;
import com.linepro.modellbahn.model.keys.ArtikelKey;
import com.linepro.modellbahn.model.keys.DecoderKey;
import com.linepro.modellbahn.model.keys.DecoderTypAdressKey;
import com.linepro.modellbahn.model.keys.DecoderTypCVKey;
import com.linepro.modellbahn.model.keys.DecoderTypFunktionKey;
import com.linepro.modellbahn.model.keys.DecoderTypKey;
import com.linepro.modellbahn.model.keys.IdKey;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.keys.ProduktKey;
import com.linepro.modellbahn.model.keys.ProduktTeilKey;
import com.linepro.modellbahn.model.keys.UnterKategorieKey;
import com.linepro.modellbahn.model.keys.VorbildKey;
import com.linepro.modellbahn.model.keys.ZugConsistKey;
import com.linepro.modellbahn.persistence.IKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.util.Selector;
import com.linepro.modellbahn.util.SelectorsBuilder;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * AbstractService.
 * Basic CRUD rest service 
 * @author $Author$
 * @version $Id$
 * 
 * @param <E> the element type
 */
public abstract class AbstractItemService<K extends IKey, E extends IItem<?>> extends AbstractService {

    protected static final List<String> PAGE_FIELDS = Arrays.asList(ApiNames.PAGE_NUMBER, ApiNames.PAGE_SIZE);

    protected static final Integer FIRST_PAGE = 0;

    protected static final Integer DEFAULT_PAGE_SIZE = 30;

    /** The persister. */
    private final IPersister<E> persister;

    private final Logger logger;

    /** The entity class. */
    private final Class<E> entityClass;

    /** The selectors. */
    private final Map<String, Selector> selectors;

    private Link apiLink;

    private Link homeLink;

    private Link wadlLink;

    private URI serviceURI;

    /**
     * Instantiates a new abstract service.
     *
     * @param entityClass the entity class
     */
    protected AbstractItemService(final Class<E> entityClass) {
        this.logger = LoggerFactory.getILoggerFactory().getLogger(getClass().getName().replace("AbstractItem", entityClass.getSimpleName()));
        this.entityClass = entityClass;
        this.persister = StaticPersisterFactory.get().createPersister(entityClass);
        this.selectors = new SelectorsBuilder().build(entityClass, JsonGetter.class);
   }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @SuppressWarnings("unchecked")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response get(Long id) {
        return get((K) new IdKey(id));
    }

    @SuppressWarnings("unchecked")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response get(String name) {
        return get((K) new NameKey(name));
    }

    /**
     * Gets the.
     *
     * @param key the key
     * @return the response
     */
    protected Response get(K key) {
        try {
            logGet(getEntityClassName() + ": " + getEntityClassName() + ": " + key);

            E entity = getPersister().findByKey(key, true);

            if (entity == null) {
                return getResponse(notFound());
            }

            return getResponse(ok(), entity, true, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    /**
     * Adds a new entity.
     *
     * @param entity the entity
     * @return the response
     */
    @ApiResponses({
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response add(E entity) {
        try {
            logPost(getEntityClassName() + ": " + entity);

            E result = getPersister().add(entity);

            return getResponse(created(), result, true, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @SuppressWarnings("unchecked")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response update(Long id, E entity) {
        return update((K) new IdKey(id), entity);
    }

    @SuppressWarnings("unchecked")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
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
    protected Response update(K id, E entity) {
        try {
            logPut(getEntityClassName() + ": " + id + ": " + entity);

            E result = getPersister().merge(id, entity);

            if (result == null) {
                return getResponse(notFound());
            }

            return getResponse(accepted(), result, true, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @SuppressWarnings("unchecked")
    @ApiResponses({
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response delete(Long id) {
        return delete((K) new IdKey(id));
    }

    @SuppressWarnings("unchecked")
    @ApiResponses({
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response delete(String name) {
        return delete((K) new NameKey(name));
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the response
     */
    protected Response delete(K id) {
        try {
            logDelete(getEntityClassName() + ": " + id);

            getPersister().delete(id);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    /**
     * Searches for entities (by example using Uri query parameters).
     *
     * @param info the info
     * @return the response
     */
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Response search(UriInfo info) {
        return search(info, info.getQueryParameters());
    }

    public Response search(UriInfo info, MultivaluedMap<String,String> queryParameters) {
        try {
            E template = getTemplate(queryParameters);

            logGet(getEntityClassName() + ": " + template);

            Integer pageNumber = null;
            Integer pageSize   = null;
            Integer maxPage    = null;
            
            String pageNumberStr = queryParameters.getFirst(ApiNames.PAGE_NUMBER);
            String pageSizeStr   = queryParameters.getFirst(ApiNames.PAGE_SIZE);

            if (pageNumberStr != null || pageSizeStr != null) {
                pageNumber = NumberUtils.toInt(pageNumberStr, FIRST_PAGE);
                pageSize   = NumberUtils.toInt(pageSizeStr, DEFAULT_PAGE_SIZE);

                Long rowCount = getPersister().countAll(template);

                maxPage = new Double(Math.floor(rowCount.doubleValue() / pageSize.doubleValue())).intValue();
            }
            
            @SuppressWarnings("unchecked")
            List<IItem<?>> entities = (List<IItem<?>>) getPersister().findAll(template, pageNumber, pageSize);
            
            if (entities.isEmpty()) {
                return getResponse(noContent());
            }

            List<Link> navigation = getNavLinks(info, pageNumber, pageSize, maxPage);

            return getResponse(ok(), entities, true, true, navigation);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    protected List<Link> getNavLinks(UriInfo info, Integer pageNumber, Integer pageSize, Integer maxPage) {
      List<Link> navigation = new ArrayList<>();

      if (pageNumber != null) {
        if (pageNumber > 0) {
          navigation.add(getPageLink(info, pageNumber-1, pageSize, ApiNames.PREVIOUS));
        }

        if (pageNumber < maxPage) {
          navigation.add(getPageLink(info, pageNumber+1, pageSize, ApiNames.NEXT));
        }
      }

      return navigation;
    }

    protected Link getPageLink(UriInfo info, Integer pageNumber, Integer pageSize, String rel) {
        // copy any non paging query parameters
        UriBuilder uri = info.getAbsolutePathBuilder();
        MultivaluedMap<String,String> queryParameters = info.getQueryParameters();

        queryParameters.forEach((k, v) -> { if (!PAGE_FIELDS.contains(k)) { v.forEach(x -> uri.queryParam(k, x)); } });
        uri.queryParam(ApiNames.PAGE_NUMBER, pageNumber.toString());
        uri.queryParam(ApiNames.PAGE_SIZE, pageSize.toString());

        Builder link = Link.fromUri(uri.toString());

        return link.rel(rel).type(GET).build();
    }

    /**
     * Createa a template for search / delete from query parameters.
     *
     * @param queryParameters the query parameters
     * @return the template
     * @throws Exception if reflection fails
     */
    private E getTemplate(MultivaluedMap<String, String> queryParameters) throws Exception {
        E template = getPersister().create();

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

    protected String getEntityClassName() {
        return entityClass.getSimpleName();
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

    protected Response getResponse(ResponseBuilder builder, List<IItem<?>> entities, boolean update, boolean delete, List<Link> navigation) {
        for (IItem<?> entity : entities) {
            entity.addLinks(getServiceURI(), update, delete);
        }
        
        return getResponse(builder.entity(new ListWithLinks<>(entities, navigation)));
    }

    protected Response getResponse(Exception e) {
        if (e instanceof PersistenceException) {
            StringBuilder message = new StringBuilder(e.getMessage());

            if (e.getCause() != null) {
                message.append(": ");
                message.append(e.getCause().getMessage());
            }

            return getResponse(badRequest(message.toString(), e.toString()));
        }
        
        return getResponse(serverError(e));
    }

    /**
     * Gets the response.
     *
     * @param builder the builder
     * @return the response
     */
    protected Response getResponse(ResponseBuilder builder) {
        return builder.links(getHomeLink(), getApiLink(), getWADLLink()).build();
    }

    protected URI getServiceURI() {
        if (serviceURI == null) {
            serviceURI = getUriInfo().getBaseUriBuilder().path(getClass()).build();
        }
        
        return serviceURI;
    }

    protected Link getApiLink() {
        if (apiLink == null) {
            apiLink = Link.fromUri(UriBuilder.fromUri(getUriInfo().getBaseUri() + ApiPaths.API_ROOT).build()).rel(ApiNames.API).type(GET).build();
        }
        
        return apiLink;
    }

    private Link getHomeLink() {
        if (homeLink == null) {
            homeLink = Link.fromUri(UriBuilder.fromUri(getUriInfo().getBaseUri()).build()).rel(ApiNames.HOME).type(GET).build();
        }
        
        return homeLink;
    }

    private Link getWADLLink() {
        if (wadlLink == null) {
            wadlLink = Link.fromUri(UriBuilder.fromUri(getUriInfo().getBaseUri()).path(ApiPaths.APPLICATION_WADL).build()).rel(ApiNames.WADL).type(GET).build();
        }

        return wadlLink;
    }

    /** Lookups that are used by more than one service */
    protected IAntrieb findAntrieb(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Antrieb.class).findByKey(name, eager); 
    }

    protected IAchsfolg findAchsfolg(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Achsfolg.class).findByKey(name, eager); 
    }

    protected IAnderung findAnderung(String artikelId, Integer anderungsId, boolean eager) throws Exception {
        return findAnderung(findArtikel(artikelId, eager), anderungsId, eager); 
    }

    protected IAnderung findAnderung(IArtikel artikel, Integer anderungsId, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Anderung.class).findByKey(new AnderungKey(artikel, anderungsId), eager); 
    }

    protected IArtikel findArtikel(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Artikel.class).findByKey(new ArtikelKey(name), eager); 
    }

    protected IAufbau findAufbau(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Aufbau.class).findByKey(name, eager); 
    }

    protected IBahnverwaltung findBahnverwaltung(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Bahnverwaltung.class).findByKey(name, eager); 
    }

    protected IDecoder findDecoder(String decoderId, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Decoder.class).findByKey(new DecoderKey(decoderId), eager); 
    }

    protected IDecoderTyp findDecoderTyp(String herstellerStr, String bestellNr, boolean eager) throws Exception {
        return findDecoderTyp(findHersteller(herstellerStr, eager), bestellNr, eager); 
    }

    private IDecoderTyp findDecoderTyp(IHersteller hersteller, String bestellNr, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(DecoderTyp.class).findByKey(new DecoderTypKey(hersteller, bestellNr), eager); 
    }

    protected IDecoderTypAdress findDecoderTypAdress(String herstellerStr, String bestellNr, Integer index, boolean eager) throws Exception {
        return findDecoderTypAdress(findDecoderTyp(herstellerStr, bestellNr, eager), index, eager) ;
    }

    protected IDecoderTypAdress findDecoderTypAdress(IDecoderTyp decoderTyp, Integer index, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(DecoderTypAdress.class).findByKey(new DecoderTypAdressKey(decoderTyp, index), eager);
    }

    protected IDecoderTypCV findDecoderTypCV(String herstellerStr, String bestellNr, Integer cv, boolean eager) throws Exception {
        return findDecoderTypCV(findDecoderTyp(herstellerStr, bestellNr, eager), cv, eager) ;
    }

    protected static IDecoderTypCV findDecoderTypCV(IDecoderTyp decoderTyp, Integer cv, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(DecoderTypCV.class).findByKey(new DecoderTypCVKey(decoderTyp, cv), eager); 
    }

    protected IDecoderTypFunktion findDecoderTypFunktion(String herstellerStr, String bestellNr, Integer reihe, String funktion, boolean eager) throws Exception {
        return findDecoderTypFunktion(findDecoderTyp(herstellerStr, bestellNr, eager), reihe, funktion, eager) ;
    }

    protected static IDecoderTypFunktion findDecoderTypFunktion(IDecoderTyp decoderTyp, Integer reihe, String funktion, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(DecoderTypFunktion.class).findByKey(new DecoderTypFunktionKey(decoderTyp, reihe, funktion), eager); 
    }
    protected IEpoch findEpoch(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Epoch.class).findByKey(name, eager); 
    }

    protected IGattung findGattung(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Gattung.class).findByKey(name, eager); 
    }

    protected IHersteller findHersteller(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Hersteller.class).findByKey(name, eager); 
    }

    protected IKategorie findKategorie(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Kategorie.class).findByKey(name, eager); 
    }

    protected IKupplung findKupplung(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Kupplung.class).findByKey(name, eager); 
    }

    protected ILicht findLicht(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Licht.class).findByKey(name, eager); 
    }

    protected IMotorTyp findMotorTyp(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(MotorTyp.class).findByKey(name, eager); 
    }

    protected IMassstab findMassstab(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Massstab.class).findByKey(name, eager); 
    }

    protected IProdukt findProdukt(String herstellerStr, String bestellNr, boolean eager) throws Exception {
        return findProdukt(findHersteller(herstellerStr, false), bestellNr, eager); 
    }
    
    protected IProdukt findProdukt(IHersteller hersteller, String bestellNr, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Produkt.class).findByKey(new ProduktKey(hersteller, bestellNr), eager); 
    }

    protected ProduktTeil findProduktTeil(String herstellerStr, String bestellNr, String teilHerstellerStr, String teilBestellNr, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(ProduktTeil.class).findByKey(new ProduktTeilKey(findProdukt(herstellerStr, bestellNr, false), findProdukt(teilHerstellerStr, teilBestellNr, false)), eager);
    }

    protected IProtokoll findProtokoll(String protokollStr, boolean eager) throws Exception  {
        return StaticPersisterFactory.get().createPersister(Protokoll.class).findByKey(new NameKey(protokollStr), eager);
    }

    protected ISonderModell findSonderModell(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(SonderModell.class).findByKey(name, eager); 
    }

    protected ISpurweite findSpurweite(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Spurweite.class).findByKey(name, eager); 
    }

    protected ISteuerung findSteuerung(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Steuerung.class).findByKey(name, eager); 
    }

    protected IUnterKategorie findUnterKategorie(String kategorie, String unterKategorie, boolean eager) throws Exception {
        return findUnterKategorie(findKategorie(kategorie, eager), unterKategorie, eager); 
    }

    protected IUnterKategorie findUnterKategorie(IKategorie kategorie, String unterKategorie, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(UnterKategorie.class).findByKey(new UnterKategorieKey(kategorie, unterKategorie), eager); 
    }

    protected IVorbild findVorbild(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Vorbild.class).findByKey(new VorbildKey(findGattung(name, false)), eager); 
    }

    protected IWahrung findWahrung(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Wahrung.class).findByKey(name, eager); 
    }

    protected IZug findZug(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(Zug.class).findByKey(name, eager); 
    }

    protected IZugConsist findZugConsist(String zugStr, Integer position, boolean eager) throws Exception {
        return findZugConsist(findZug(zugStr, eager), position, eager) ;
    }

    protected IZugConsist findZugConsist(IZug zug, Integer position, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(ZugConsist.class).findByKey(new ZugConsistKey(zug, position), eager);
    }

    protected IZugTyp findZugTyp(String name, boolean eager) throws Exception {
        return StaticPersisterFactory.get().createPersister(ZugTyp.class).findByKey(name, eager); 
    }

    protected void setServiceURI(URI serviceURI) {
        this.serviceURI = serviceURI;
    }
}