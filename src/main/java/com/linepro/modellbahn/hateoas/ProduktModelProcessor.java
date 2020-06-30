package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.ProduktModel;

@Lazy
@Component(PREFIX + "ProduktModelProcessor")
public class ProduktModelProcessor extends ModelProcessorImpl<ProduktModel> implements RepresentationModelProcessor<ProduktModel> {

    private static final String HERSTELLER = "{" + ApiNames.HERSTELLER + "}";
    private static final String BESTELL_NR = "{" + ApiNames.BESTELL_NR + "}";
    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { HERSTELLER, ((ProduktModel) m).getHersteller() }, 
        { BESTELL_NR, ((ProduktModel) m).getBestellNr() }, 
        });

    @Autowired
    public ProduktModelProcessor() {
        super(
            new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_PRODUKT, EXTRACTOR), 
            new LinkTemplateImpl(ApiRels.SELF, ApiPaths.GET_PRODUKT, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.SEARCH, ApiPaths.SEARCH_PRODUKT, EXTRACTOR), 
            new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_PRODUKT, EXTRACTOR, (m) -> BooleanUtils.isFalse(((ProduktModel) m).getDeleted())),
            new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_PRODUKT, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.IMAGE, ApiPaths.ADD_PRODUKT_ABBILDUNG, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.INSTRUCTIONS, ApiPaths.ADD_PRODUKT_ANLEITUNGEN, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.PARTS_DIAGRAM, ApiPaths.ADD_PRODUKT_EXPLOSIONSZEICHNUNG, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.PARTS, ApiPaths.ADD_PRODUKT_TEIL, EXTRACTOR)
            );
    }
}
