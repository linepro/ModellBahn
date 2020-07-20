package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ACHSFOLG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_ACHSFOLG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_ACHSFOLG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_ACHSFOLG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_ACHSFOLG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.AchsfolgModel;

@Lazy
@Component(PREFIX + "AchsfolgModelProcessor")
public class AchsfolgModelProcessor extends NamedModelProcessor<AchsfolgModel> implements RepresentationModelProcessor<AchsfolgModel> {

    @Autowired
    public AchsfolgModelProcessor() {
        super(
            ADD_ACHSFOLG,
            GET_ACHSFOLG,
            UPDATE_ACHSFOLG,
            DELETE_ACHSFOLG,
            SEARCH_ACHSFOLG
            );
    }
}
