package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import org.apache.commons.beanutils.ConvertUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/unterkategorien")
public class UnterKategorieService extends NamedItemService<UnterKategorie> {

    public UnterKategorieService() {
        super(UnterKategorie.class);
    }

    @JsonCreator
    public UnterKategorie create(@JsonProperty(value="id", required=false) Long id, 
                    @JsonProperty(value="kategorie", required=false) String kategorieStr, 
                    @JsonProperty(value="name", required=false) String name, 
                    @JsonProperty(value="description", required=false) String bezeichnung, 
                    @JsonProperty(value="deleted", required=false) Boolean deleted) throws Exception {
        UnterKategorie entity = create();

        entity.setId(id);
        entity.setName(name);
        entity.setKategorie((Kategorie) ConvertUtils.convert(kategorieStr, Kategorie.class));
        entity.setBezeichnung(bezeichnung);
        entity.setDeleted(deleted);

        return entity;
    }
}