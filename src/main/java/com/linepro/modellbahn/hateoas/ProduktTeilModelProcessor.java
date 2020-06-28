package com.linepro.modellbahn.hateoas;

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
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.model.SoftDelete;

@Lazy
@Component("ProduktTeilModelProcessor")
public class ProduktTeilModelProcessor extends ModelProcessorImpl<ProduktTeilModel> implements RepresentationModelProcessor<ProduktTeilModel> {

    private static final String HERSTELLER = "{" + ApiNames.HERSTELLER + "}";
    private static final String BESTELL_NR = "{" + ApiNames.BESTELL_NR + "}";
    private static final String TEIL_HERSTELLER = "{" + ApiNames.TEIL_HERSTELLER + "}";
    private static final String TEIL_BESTELL_NR = "{" + ApiNames.TEIL_BESTELL_NR + "}";
    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String, Object>(), new String[][] {
                        { HERSTELLER, ((ProduktTeilModel) m).getHersteller() }, 
                        { BESTELL_NR, ((ProduktTeilModel) m).getBestellNr() }, 
                        { TEIL_HERSTELLER, ((ProduktTeilModel) m).getTeilHersteller() },
                        { TEIL_BESTELL_NR, ((ProduktTeilModel) m).getTeilBestellNr() }
                        });

    @Autowired
    public ProduktTeilModelProcessor() {
        super(
              new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_PRODUKT_TEIL, EXTRACTOR),
              new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_PRODUKT_TEIL, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted()))
              );
    }
}
