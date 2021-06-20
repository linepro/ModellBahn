package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.DECODER_ID;
import static com.linepro.modellbahn.controller.impl.ApiNames.INDEX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_DECODER_ADRESS;
import static com.linepro.modellbahn.controller.impl.ApiRels.PARENT;
import static com.linepro.modellbahn.controller.impl.ApiRels.UPDATE;

import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.DecoderAdressModel;

@Lazy
@Component(PREFIX + "DecoderAdressModelProcessor")
public class DecoderAdressModelProcessor extends ModelProcessorImpl<DecoderAdressModel> implements RepresentationModelProcessor<DecoderAdressModel> {

    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { DECODER_ID, ((DecoderAdressModel) m).getDecoderId() }, 
            { INDEX, String.valueOf(((DecoderAdressModel) m).getIndex()) } 
            });

    @Autowired
    public DecoderAdressModelProcessor() {
        super(
            new LinkTemplateImpl(PARENT, GET_DECODER, EXTRACTOR),
            new LinkTemplateImpl(UPDATE, UPDATE_DECODER_ADRESS, EXTRACTOR)
            );
    }
}
