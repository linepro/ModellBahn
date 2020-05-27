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
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.model.SoftDelete;

@Lazy
@Component
public class DecoderTypCvModelProcessor extends ModelProcessorImpl<DecoderTypCvModel> implements RepresentationModelProcessor<DecoderTypCvModel> {

    private static final String HERSTELLER = "{" + ApiNames.HERSTELLER + "}";
    private static final String BESTELL_NR = "{" + ApiNames.BESTELL_NR + "}";
    private static final String CV = "{" + ApiNames.CV + "}";

    @Autowired
    public DecoderTypCvModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { HERSTELLER, ((DecoderTypCvModel) m).getHersteller() }, 
            { BESTELL_NR, ((DecoderTypCvModel) m).getBestellNr() }, 
            { CV, String.valueOf(((DecoderTypCvModel) m).getCv()) } 
            }),
                        new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_DECODER_TYP_CV),
                        new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_DECODER_TYP_CV, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted())));
    }
}
