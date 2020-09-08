package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_VORBILD;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_VORBILD_ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_VORBILD;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_VORBILD;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_VORBILD;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_VORBILD;
import static com.linepro.modellbahn.controller.impl.ApiRels.ABBILDUNG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.VorbildModel;

@Lazy
@Component(PREFIX + "VorbildModelProcessor")
public class VorbildModelProcessor extends NamedModelProcessor<VorbildModel> implements RepresentationModelProcessor<VorbildModel> {

    @Autowired
    public VorbildModelProcessor() {
        super(
            ADD_VORBILD,
            GET_VORBILD,
            UPDATE_VORBILD,
            DELETE_VORBILD,
            SEARCH_VORBILD,
            new LinkTemplateImpl(ABBILDUNG, ADD_VORBILD_ABBILDUNG, EXTRACTOR)
            );
    }
}
