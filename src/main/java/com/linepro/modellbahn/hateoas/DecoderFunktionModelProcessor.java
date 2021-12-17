package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.DECODER_ID;
import static com.linepro.modellbahn.controller.impl.ApiNames.FUNKTION;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_DECODER_FUNKTION;
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
import com.linepro.modellbahn.model.DecoderFunktionModel;

@Lazy
@Component(PREFIX + "DecoderFunktionModelProcessor")
public class DecoderFunktionModelProcessor extends ModelProcessorImpl<DecoderFunktionModel> implements RepresentationModelProcessor<DecoderFunktionModel> {

    private static FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { DECODER_ID, ((DecoderFunktionModel) m).getDecoderId() }, 
        { FUNKTION, String.valueOf(((DecoderFunktionModel) m).getFunktion()) } 
        });

   @Autowired
    public DecoderFunktionModelProcessor() {
        super(
            new LinkTemplateImpl(PARENT, GET_DECODER, EXTRACTOR ),
            new LinkTemplateImpl(UPDATE, UPDATE_DECODER_FUNKTION, EXTRACTOR )
            );
    }
}
