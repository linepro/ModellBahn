package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.AchsfolgModel;

@Lazy
@Component
public class AchsfolgModelProcessor extends NamedModelProcessor<AchsfolgModel> implements RepresentationModelProcessor<AchsfolgModel> {

    @Autowired
    public AchsfolgModelProcessor() {
        super(ApiPaths.ADD_ACHSFOLG, ApiPaths.GET_ACHSFOLG, ApiPaths.UPDATE_ACHSFOLG, ApiPaths.DELETE_ACHSFOLG, ApiPaths.SEARCH_ACHSFOLG);
    }
}
