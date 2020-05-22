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
import com.linepro.modellbahn.model.DecoderTypCvModel;

@Lazy
@Component
public class DecoderTypCvModelProcessor extends ModelProcessorImpl<DecoderTypCvModel> implements RepresentationModelProcessor<DecoderTypCvModel> {

    @Autowired
    public DecoderTypCvModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { "{" + ApiNames.HERSTELLER + "}", ((DecoderTypCvModel) m).getHersteller() }, 
            { "{" + ApiNames.BESTELL_NR + "}", ((DecoderTypCvModel) m).getBestellNr() }, 
            { "{" + ApiNames.ANDERUNG_ID + "}", String.valueOf(((DecoderTypCvModel) m).getCv()) } 
            }),
                        new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_DECODER_TYP_CV),
                        new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_DECODER_TYP_CV));
    }
}
