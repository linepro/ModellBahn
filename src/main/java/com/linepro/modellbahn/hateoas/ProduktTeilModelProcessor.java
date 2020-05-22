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
import com.linepro.modellbahn.model.ProduktTeilModel;

@Lazy
@Component
public class ProduktTeilModelProcessor extends ModelProcessorImpl<ProduktTeilModel> implements RepresentationModelProcessor<ProduktTeilModel> {

    @Autowired
    public ProduktTeilModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String, Object>(), new String[][] {
                        { "{" + ApiNames.HERSTELLER + "}", ((ProduktTeilModel) m).getHersteller() }, 
                        { "{" + ApiNames.BESTELL_NR + "}", ((ProduktTeilModel) m).getBestellNr() }, 
                        { "{" + ApiNames.TEIL_HERSTELLER + "}", ((ProduktTeilModel) m).getTeilHersteller() },
                        { "{" + ApiNames.TEIL_BESTELL_NR + "}", ((ProduktTeilModel) m).getTeilBestellNr() }
                        }),
              new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_PRODUKT_TEIL),
              new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_PRODUKT_TEIL));
    }
}
