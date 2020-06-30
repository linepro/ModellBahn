package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Collections;

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
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.model.SoftDelete;

@Lazy
@Component(PREFIX + "DecoderModelProcessor")
public class DecoderModelProcessor extends ModelProcessorImpl<DecoderModel> implements RepresentationModelProcessor<DecoderModel> {

    private static final String DECODER_ID = "{" + ApiNames.DECODER_ID + "}";
    
    private static final FieldsExtractor EXTRACTOR = (m) -> Collections.singletonMap(DECODER_ID, ((DecoderModel) m).getDecoderId());

    @Autowired
    public DecoderModelProcessor() {
        super(
            new LinkTemplateImpl(ApiRels.SELF, ApiPaths.GET_DECODER, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_DECODER, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_DECODER, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted())),
            new LinkTemplateImpl(ApiRels.SEARCH, ApiPaths.SEARCH_DECODER, EXTRACTOR)
            );
    }
}
