package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_PROTOKOLL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_PROTOKOLL_ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_PROTOKOLL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_PROTOKOLL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_PROTOKOLL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_PROTOKOLL;
import static com.linepro.modellbahn.controller.impl.ApiRels.ABBILDUNG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.ProtokollModel;

@Lazy
@Component(PREFIX + "ProtokollModelProcessor")
public class ProtokollModelProcessor extends NamedModelProcessor<ProtokollModel> implements RepresentationModelProcessor<ProtokollModel> {

    @Autowired
    public ProtokollModelProcessor() {
        super(ADD_PROTOKOLL, GET_PROTOKOLL, UPDATE_PROTOKOLL, DELETE_PROTOKOLL, SEARCH_PROTOKOLL,
                        new LinkTemplateImpl(ABBILDUNG, ADD_PROTOKOLL_ABBILDUNG, EXTRACTOR));
    }
}
