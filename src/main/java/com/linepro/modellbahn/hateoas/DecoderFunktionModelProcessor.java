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
import com.linepro.modellbahn.model.DecoderFunktionModel;

@Lazy
@Component
public class DecoderFunktionModelProcessor extends ModelProcessorImpl<DecoderFunktionModel> implements RepresentationModelProcessor<DecoderFunktionModel> {

    private static final String DECODER_ID = "{" + ApiNames.DECODER_ID + "}";
    private static final String REIHE = "{" + ApiNames.REIHE + "}";
    private static final String FUNKTION = "{" + ApiNames.FUNKTION + "}";

   @Autowired
    public DecoderFunktionModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { DECODER_ID, ((DecoderFunktionModel) m).getDecoderId() }, 
            { REIHE, String.valueOf(((DecoderFunktionModel) m).getReihe()) }, 
            { FUNKTION, String.valueOf(((DecoderFunktionModel) m).getFunktion()) } 
            }),
                        new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_DECODER_FUNKTION));
    }
}
