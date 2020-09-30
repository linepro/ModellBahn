package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_LICHT;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_LICHT_ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_LICHT;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_LICHT;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_LICHT;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_LICHT;

import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.LichtModel;

@Lazy
@Component(PREFIX + "LichtModelProcessor")
public class LichtModelProcessor extends NamedModelProcessor<LichtModel> implements RepresentationModelProcessor<LichtModel> {

    public LichtModelProcessor() {
        super(
            ADD_LICHT,
            GET_LICHT,
            UPDATE_LICHT,
            DELETE_LICHT,
            SEARCH_LICHT,
            new LinkTemplateImpl(ApiRels.ABBILDUNG, ADD_LICHT_ABBILDUNG, EXTRACTOR)
            );
    }
}
