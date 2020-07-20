package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.DECODER_ID;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_DECODER;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.SEARCH;
import static com.linepro.modellbahn.controller.impl.ApiRels.SELF;
import static com.linepro.modellbahn.controller.impl.ApiRels.UPDATE;

import java.util.Collections;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.model.SoftDelete;

@Lazy
@Component(PREFIX + "DecoderModelProcessor")
public class DecoderModelProcessor extends ModelProcessorImpl<DecoderModel> implements RepresentationModelProcessor<DecoderModel> {
    
    private static final FieldsExtractor EXTRACTOR = (m) -> Collections.singletonMap(DECODER_ID, ((DecoderModel) m).getDecoderId());

    @Autowired
    public DecoderModelProcessor() {
        super(
            new LinkTemplateImpl(SELF, GET_DECODER, EXTRACTOR),
            new LinkTemplateImpl(UPDATE, UPDATE_DECODER, EXTRACTOR),
            new LinkTemplateImpl(DELETE, DELETE_DECODER, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted())),
            new LinkTemplateImpl(SEARCH, SEARCH_DECODER, EXTRACTOR)
            );
    }
}
