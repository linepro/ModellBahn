package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.BESTELL_NR;
import static com.linepro.modellbahn.controller.impl.ApiNames.FUNKTION;
import static com.linepro.modellbahn.controller.impl.ApiNames.HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiNames.REIHE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_DECODER_TYP_FUNKTION;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_DECODER_TYP_FUNKTION;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_DECODER_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_DECODER_TYP_FUNKTION;
import static com.linepro.modellbahn.controller.impl.ApiRels.ADD;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.PARENT;
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
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.model.SoftDelete;

@Lazy
@Component(PREFIX + "DecoderTypFunktionModelProcessor")
public class DecoderTypFunktionModelProcessor extends ModelProcessorImpl<DecoderTypFunktionModel> implements RepresentationModelProcessor<DecoderTypFunktionModel> {

    private static final FieldsExtractor EXTRACTOR =(m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { HERSTELLER, ((DecoderTypFunktionModel) m).getHersteller() }, 
        { BESTELL_NR, ((DecoderTypFunktionModel) m).getBestellNr() }, 
        { REIHE, String.valueOf(((DecoderTypFunktionModel) m).getReihe()) }, 
        { FUNKTION, ((DecoderTypFunktionModel) m).getFunktion() } 
        });
    
    @Autowired
    public DecoderTypFunktionModelProcessor() {
        super(
            new LinkTemplateImpl(PARENT, GET_DECODER_TYP, EXTRACTOR),
            new LinkTemplateImpl(ADD, ADD_DECODER_TYP_FUNKTION, EXTRACTOR),
            new LinkTemplateImpl(UPDATE, UPDATE_DECODER_TYP_FUNKTION, EXTRACTOR),
            new LinkTemplateImpl(DELETE, DELETE_DECODER_TYP_FUNKTION, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted()))
            );
    }
}
