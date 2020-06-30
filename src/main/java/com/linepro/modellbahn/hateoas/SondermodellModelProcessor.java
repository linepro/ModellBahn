package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.SondermodellModel;

@Lazy
@Component(PREFIX + "SondermodellModelProcessor")
public class SondermodellModelProcessor extends NamedModelProcessor<SondermodellModel> implements RepresentationModelProcessor<SondermodellModel> {

    @Autowired
    public SondermodellModelProcessor() {
        super(
            ApiPaths.ADD_SONDERMODELL, 
            ApiPaths.GET_SONDERMODELL, 
            ApiPaths.UPDATE_SONDERMODELL, 
            ApiPaths.DELETE_SONDERMODELL, 
            ApiPaths.SEARCH_SONDERMODELL
            );
    }
}
