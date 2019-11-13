package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.persistence.repository.IUnterKategorieRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@JsonComponent
public class UnterKategorieDeserializer extends AbstractItemDeserializer<IUnterKategorie> {

    private final IUnterKategorieRepository perisister;

    @Autowired
    protected UnterKategorieDeserializer(IUnterKategorieRepository perisister) {
        this.perisister = perisister;
    }

    @Override
    public IUnterKategorie findItem(ObjectNode node) throws Exception {
        String kategorieStr = node.get(ApiNames.KATEGORIE).get(ApiNames.NAMEN).asText();
        String unterKategorieStr = node.get(ApiNames.NAMEN).asText();

        return perisister.findByKategorieAndName(kategorieStr, unterKategorieStr);
    } 
}
