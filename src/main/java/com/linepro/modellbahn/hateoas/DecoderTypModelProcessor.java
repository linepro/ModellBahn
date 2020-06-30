package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

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
import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.model.SoftDelete;

@Lazy
@Component(PREFIX + "DecoderTypModelProcessor")
public class DecoderTypModelProcessor extends ModelProcessorImpl<DecoderTypModel> implements RepresentationModelProcessor<DecoderTypModel> {

    private static final String HERSTELLER = "{" + ApiNames.HERSTELLER + "}";
    private static final String BESTELL_NR = "{" + ApiNames.BESTELL_NR + "}";

    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { HERSTELLER, ((DecoderTypModel) m).getHersteller() }, 
        { BESTELL_NR, ((DecoderTypModel) m).getBestellNr() }, 
        });
    
    @Autowired
    public DecoderTypModelProcessor() {
        super(
            new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_DECODER_TYP, EXTRACTOR), 
            new LinkTemplateImpl(ApiRels.SELF, ApiPaths.GET_DECODER_TYP, EXTRACTOR), 
            new LinkTemplateImpl(ApiRels.SEARCH, ApiPaths.SEARCH_DECODER_TYP, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_DECODER_TYP, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted())),
            new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_DECODER_TYP, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.INSTRUCTIONS, ApiPaths.ADD_DECODER_TYP_ANLEITUNGEN, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_DECODER_TYP_ADRESS, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_DECODER_TYP_CV, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_DECODER_TYP_FUNKTION, EXTRACTOR)
            );
    }
}
