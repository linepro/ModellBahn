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
import com.linepro.modellbahn.model.DecoderTypModel;

@Lazy
@Component
public class DecoderTypModelProcessor extends ModelProcessorImpl<DecoderTypModel> implements RepresentationModelProcessor<DecoderTypModel> {

    @Autowired
    public DecoderTypModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { "{" + ApiNames.HERSTELLER + "}", ((DecoderTypModel) m).getHersteller().getName() }, 
            { "{" + ApiNames.BESTELL_NR + "}", ((DecoderTypModel) m).getBestellNr() }, 
            }),
                        new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_DECODER_TYP), 
                        new LinkTemplateImpl(ApiRels.SELF, ApiPaths.GET_DECODER_TYP), 
                        new LinkTemplateImpl(ApiRels.SEARCH, ApiPaths.SEARCH_DECODER_TYP),
                        new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_DECODER_TYP),
                        new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_DECODER_TYP),
                        new LinkTemplateImpl(ApiRels.ADD_INSTRUCTIONS, ApiPaths.ADD_DECODER_TYP_ANLEITUNGEN),
                        new LinkTemplateImpl(ApiRels.REMOVE_INSTRUCTIONS, ApiPaths.DELETE_DECODER_TYP_ANLEITUNGEN),
                        new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_DECODER_TYP_ADRESS),
                        new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_DECODER_TYP_CV),
                        new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_DECODER_TYP_FUNKTION));
    }
}
