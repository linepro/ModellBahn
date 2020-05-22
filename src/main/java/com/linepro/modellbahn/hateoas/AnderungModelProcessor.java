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
import com.linepro.modellbahn.model.AnderungModel;

@Lazy
@Component
public class AnderungModelProcessor extends ModelProcessorImpl<AnderungModel> implements RepresentationModelProcessor<AnderungModel> {

    @Autowired
    public AnderungModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { "{" + ApiNames.ARTIKEL_ID + "}", ((AnderungModel) m).getArtikelId() }, 
            { "{" + ApiNames.ANDERUNG_ID + "}", String.valueOf(((AnderungModel) m).getAnderungId()) } 
            }),
                        new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_ANDERUNG),
                        new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_ANDERUNG));
    }
}
