package com.linepro.modellbahn.hateoas;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.model.DecoderModel;

@Lazy
@Component
public class DecoderModelProcessor extends ModelProcessorImpl<DecoderModel> implements RepresentationModelProcessor<DecoderModel> {

    @Autowired
    public DecoderModelProcessor() {
        super((m) -> Collections.singletonMap("{" + ApiNames.DECODER_ID + "}", ((ArtikelModel) m).getArtikelId()),
                        new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_DECODER),
                        new LinkTemplateImpl(ApiRels.SELF, ApiPaths.GET_DECODER),
                        new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_DECODER),
                        new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_DECODER),
                        new LinkTemplateImpl(ApiRels.SEARCH, ApiPaths.SEARCH_DECODER));
    }
}
