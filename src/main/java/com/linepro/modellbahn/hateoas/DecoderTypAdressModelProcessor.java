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
import com.linepro.modellbahn.model.DecoderTypAdressModel;

@Lazy
@Component
public class DecoderTypAdressModelProcessor extends ModelProcessorImpl<DecoderTypAdressModel> implements RepresentationModelProcessor<DecoderTypAdressModel> {

    @Autowired
    public DecoderTypAdressModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { "{" + ApiNames.HERSTELLER + "}", ((DecoderTypAdressModel) m).getHersteller() }, 
            { "{" + ApiNames.BESTELL_NR + "}", ((DecoderTypAdressModel) m).getBestellNr() }, 
            { "{" + ApiNames.INDEX + "}", String.valueOf(((DecoderTypAdressModel) m).getIndex()) } 
            }),
                        new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_DECODER_TYP_ADRESS),
                        new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_DECODER_TYP_ADRESS));
    }
}
