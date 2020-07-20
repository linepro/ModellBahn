package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.BESTELL_NR;
import static com.linepro.modellbahn.controller.impl.ApiNames.HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiNames.TEIL_BESTELL_NR;
import static com.linepro.modellbahn.controller.impl.ApiNames.TEIL_HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_PRODUKT_TEIL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_PRODUKT_TEIL;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
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
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.model.SoftDelete;

@Lazy
@Component(PREFIX + "ProduktTeilModelProcessor")
public class ProduktTeilModelProcessor extends ModelProcessorImpl<ProduktTeilModel> implements RepresentationModelProcessor<ProduktTeilModel> {

    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String, Object>(), new String[][] {
                        { HERSTELLER, ((ProduktTeilModel) m).getHersteller() }, 
                        { BESTELL_NR, ((ProduktTeilModel) m).getBestellNr() }, 
                        { TEIL_HERSTELLER, ((ProduktTeilModel) m).getTeilHersteller() },
                        { TEIL_BESTELL_NR, ((ProduktTeilModel) m).getTeilBestellNr() }
                        });

    @Autowired
    public ProduktTeilModelProcessor() {
        super(
              new LinkTemplateImpl(UPDATE, UPDATE_PRODUKT_TEIL, EXTRACTOR),
              new LinkTemplateImpl(DELETE, DELETE_PRODUKT_TEIL, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted()))
              );
    }
}
