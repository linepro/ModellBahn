package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.BESTELL_NR;
import static com.linepro.modellbahn.controller.impl.ApiNames.HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_PRODUKT;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_PRODUKT_ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_PRODUKT_ANLEITUNGEN;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_PRODUKT_EXPLOSIONSZEICHNUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_PRODUKT_TEIL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_PRODUKT;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_PRODUKT;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_PRODUKT;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_PRODUKT;
import static com.linepro.modellbahn.controller.impl.ApiRels.ADD;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.IMAGE;
import static com.linepro.modellbahn.controller.impl.ApiRels.INSTRUCTIONS;
import static com.linepro.modellbahn.controller.impl.ApiRels.PARTS;
import static com.linepro.modellbahn.controller.impl.ApiRels.PARTS_DIAGRAM;
import static com.linepro.modellbahn.controller.impl.ApiRels.SEARCH;
import static com.linepro.modellbahn.controller.impl.ApiRels.SELF;
import static com.linepro.modellbahn.controller.impl.ApiRels.UPDATE;

import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.ProduktModel;

@Lazy
@Component(PREFIX + "ProduktModelProcessor")
public class ProduktModelProcessor extends ModelProcessorImpl<ProduktModel> implements RepresentationModelProcessor<ProduktModel> {

    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { HERSTELLER, ((ProduktModel) m).getHersteller() }, 
        { BESTELL_NR, ((ProduktModel) m).getBestellNr() }, 
        });

    @Autowired
    public ProduktModelProcessor() {
        super(
            new LinkTemplateImpl(ADD, ADD_PRODUKT, EXTRACTOR), 
            new LinkTemplateImpl(SELF, GET_PRODUKT, EXTRACTOR),
            new LinkTemplateImpl(SEARCH, SEARCH_PRODUKT, EXTRACTOR), 
            new LinkTemplateImpl(DELETE, DELETE_PRODUKT, EXTRACTOR, (m) -> BooleanUtils.isFalse(((ProduktModel) m).getDeleted())),
            new LinkTemplateImpl(UPDATE, UPDATE_PRODUKT, EXTRACTOR),
            new LinkTemplateImpl(IMAGE, ADD_PRODUKT_ABBILDUNG, EXTRACTOR),
            new LinkTemplateImpl(INSTRUCTIONS, ADD_PRODUKT_ANLEITUNGEN, EXTRACTOR),
            new LinkTemplateImpl(PARTS_DIAGRAM, ADD_PRODUKT_EXPLOSIONSZEICHNUNG, EXTRACTOR),
            new LinkTemplateImpl(PARTS, ADD_PRODUKT_TEIL, EXTRACTOR)
            );
    }
}
