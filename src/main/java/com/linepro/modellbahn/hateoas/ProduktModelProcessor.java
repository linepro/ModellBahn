package com.linepro.modellbahn.hateoas;

import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.ProduktModel;

@Lazy
@Component
public class ProduktModelProcessor extends ModelProcessorImpl<ProduktModel> implements RepresentationModelProcessor<ProduktModel> {

    @Autowired
    public ProduktModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { "{" + ApiNames.HERSTELLER + "}", ((ProduktModel) m).getHersteller().getName() }, 
            { "{" + ApiNames.BESTELL_NR + "}", ((ProduktModel) m).getBestellNr() }, 
            }),
                        new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_PRODUKT), 
                        new LinkTemplateImpl(ApiRels.SELF, ApiPaths.GET_PRODUKT),
                        new LinkTemplateImpl(ApiRels.SEARCH, ApiPaths.SEARCH_PRODUKT), 
                        new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_PRODUKT),
                        new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_PRODUKT),
                        new LinkTemplateImpl(ApiRels.ADD_IMAGE, ApiPaths.ADD_PRODUKT_ABBILDUNG),
                        new LinkTemplateImpl(ApiRels.REMOVE_IMAGE, ApiPaths.DELETE_PRODUKT_ABBILDUNG),
                        new LinkTemplateImpl(ApiRels.ADD_INSTRUCTIONS, ApiPaths.ADD_PRODUKT_ANLEITUNGEN),
                        new LinkTemplateImpl(ApiRels.REMOVE_INSTRUCTIONS, ApiPaths.DELETE_PRODUKT_ANLEITUNGEN),
                        new LinkTemplateImpl(ApiRels.ADD_PARTS_DIAGRAM, ApiPaths.ADD_PRODUKT_EXPLOSIONSZEICHNUNG),
                        new LinkTemplateImpl(ApiRels.REMOVE_PARTS_DIAGRAM, ApiPaths.DELETE_PRODUKT_EXPLOSIONSZEICHNUNG),
                        new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_PRODUKT_TEIL)                        
                        );
    }
}
