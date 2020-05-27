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
import com.linepro.modellbahn.model.DecoderCvModel;

@Lazy
@Component
public class DecoderCvModelProcessor extends ModelProcessorImpl<DecoderCvModel> implements RepresentationModelProcessor<DecoderCvModel> {

    private static final String DECODER_ID = "{" + ApiNames.DECODER_ID + "}";
    private static final String CV = "{" + ApiNames.CV + "}";

    @Autowired
    public DecoderCvModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { DECODER_ID, ((DecoderCvModel) m).getDecoderId() }, 
            { CV, String.valueOf(((DecoderCvModel) m).getCv()) } 
            }),
                        new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_DECODER_CV));
    }
}
