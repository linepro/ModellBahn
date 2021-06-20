package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.BESTELL_NR;
import static com.linepro.modellbahn.controller.impl.ApiNames.CV;
import static com.linepro.modellbahn.controller.impl.ApiNames.HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_DECODER_TYP_CV;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_DECODER_TYP_CV;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_DECODER_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_DECODER_TYP_CV;
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
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.model.SoftDelete;

@Lazy
@Component(PREFIX + "DecoderTypCvModelProcessor")
public class DecoderTypCvModelProcessor extends ModelProcessorImpl<DecoderTypCvModel> implements RepresentationModelProcessor<DecoderTypCvModel> {

    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { HERSTELLER, ((DecoderTypCvModel) m).getHersteller() }, 
        { BESTELL_NR, ((DecoderTypCvModel) m).getBestellNr() }, 
        { CV, String.valueOf(((DecoderTypCvModel) m).getCv()) } 
        });

    @Autowired
    public DecoderTypCvModelProcessor() {
        super(
            new LinkTemplateImpl(PARENT, GET_DECODER_TYP, EXTRACTOR),
            new LinkTemplateImpl(ADD, ADD_DECODER_TYP_CV, EXTRACTOR),
            new LinkTemplateImpl(UPDATE, UPDATE_DECODER_TYP_CV, EXTRACTOR),
            new LinkTemplateImpl(DELETE, DELETE_DECODER_TYP_CV, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted())));
    }
}
