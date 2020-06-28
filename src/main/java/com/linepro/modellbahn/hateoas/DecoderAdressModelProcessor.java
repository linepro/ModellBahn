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
import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.DecoderAdressModel;

@Lazy
@Component("DecoderAdressModelProcessor")
public class DecoderAdressModelProcessor extends ModelProcessorImpl<DecoderAdressModel> implements RepresentationModelProcessor<DecoderAdressModel> {

    private static final String DECODER_ID = "{" + ApiNames.DECODER_ID + "}";
    private static final String INDEX = "{" + ApiNames.INDEX + "}";
    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { DECODER_ID, ((DecoderAdressModel) m).getDecoderId() }, 
            { INDEX, String.valueOf(((DecoderAdressModel) m).getIndex()) } 
            });

    @Autowired
    public DecoderAdressModelProcessor() {
        super(
            new LinkTemplateImpl(ApiRels.PARENT, ApiPaths.GET_DECODER, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_DECODER_ADRESS, EXTRACTOR)
            );
    }
}
