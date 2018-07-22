package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/kategorien")
public class KategorieService extends NamedItemService<Kategorie> {

    protected final IPersister<UnterKategorie> unterKategoriePersister;

    public KategorieService() {
        super(Kategorie.class);

        unterKategoriePersister = StaticPersisterFactory.get().createNamedItemPersister(UnterKategorie.class);
        
    }

    @JsonCreator
    public UnterKategorie create(@JsonProperty(value="id", required=false) Long id, 
                    @JsonProperty(value="kategorie", required=false) String kategorieStr, 
                    @JsonProperty(value="name", required=false) String name, 
                    @JsonProperty(value="description", required=false) String bezeichnung, 
                    @JsonProperty(value="deleted", required=false) Boolean deleted) throws Exception {
        Kategorie kat = findKategorie(kategorieStr);

        UnterKategorie entity = new UnterKategorie(id, kat, name, bezeichnung, deleted);

        return entity;
    }

    protected Kategorie findKategorie(String kategorieStr) throws Exception {
        return ((IPersister<Kategorie>) getPersister()).findByKey(kategorieStr);
    }

    @GET
    @Path("/{kategorie}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("kategorie") String kategorie, @PathParam("name") String name) {
        return getResponse(null);
    }

    @POST
    @Path("/{kategorie}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response add(@PathParam("kategorie") String kategorie, UnterKategorie unterkategorie) throws Exception {
        return getResponse(null);
    }

    @PUT
    @Path("/{kategorie}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("kategorie") String kategorie, @PathParam("name") String name, UnterKategorie entity) {
        return getResponse(null);
    }

    @DELETE
    @Path("/{kategorie}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("kategorie") String kategorie, @PathParam("name") String name) {
        return getResponse(null);
    }
}
