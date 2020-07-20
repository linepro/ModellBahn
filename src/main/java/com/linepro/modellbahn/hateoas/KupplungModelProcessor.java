package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_KUPPLUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_KUPPLUNG_ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_KUPPLUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_KUPPLUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_KUPPLUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_KUPPLUNG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.KupplungModel;

@Component(PREFIX + "KupplungModelProcessor")
public class KupplungModelProcessor extends NamedModelProcessor<KupplungModel> implements RepresentationModelProcessor<KupplungModel> {

    @Autowired
    public KupplungModelProcessor() {
        super(
            ADD_KUPPLUNG,
            GET_KUPPLUNG,
            UPDATE_KUPPLUNG,
            DELETE_KUPPLUNG,
            SEARCH_KUPPLUNG,
            new LinkTemplateImpl(ApiRels.IMAGE, ADD_KUPPLUNG_ABBILDUNG, EXTRACTOR)
            );
    }
}
