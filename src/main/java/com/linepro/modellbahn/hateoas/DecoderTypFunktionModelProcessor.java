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
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

@Lazy
@Component
public class DecoderTypFunktionModelProcessor extends ModelProcessorImpl<DecoderTypFunktionModel> implements RepresentationModelProcessor<DecoderTypFunktionModel> {

    @Autowired
    public DecoderTypFunktionModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { "{" + ApiNames.HERSTELLER + "}", ((DecoderTypFunktionModel) m).getHersteller() }, 
            { "{" + ApiNames.BESTELL_NR + "}", ((DecoderTypFunktionModel) m).getBestellNr() }, 
            { "{" + ApiNames.REIHE + "}", String.valueOf(((DecoderTypFunktionModel) m).getReihe()) }, 
            { "{" + ApiNames.FUNKTION + "}", ((DecoderTypFunktionModel) m).getFunktion() } 
            }),
                        new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_DECODER_TYP_FUNKTION),
                        new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_DECODER_TYP_FUNKTION));
    }
}
