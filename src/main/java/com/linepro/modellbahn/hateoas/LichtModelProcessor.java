package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.LichtModel;

@Lazy
@Component(PREFIX + "LichtModelProcessor")
public class LichtModelProcessor extends NamedModelProcessor<LichtModel> implements RepresentationModelProcessor<LichtModel> {

    public LichtModelProcessor() {
        super(
            ApiPaths.ADD_LICHT,
            ApiPaths.GET_LICHT,
            ApiPaths.UPDATE_LICHT,
            ApiPaths.DELETE_LICHT,
            ApiPaths.SEARCH_LICHT,
            new LinkTemplateImpl(ApiRels.IMAGE, ApiPaths.ADD_LICHT_ABBILDUNG, EXTRACTOR)
            );
    }
}
