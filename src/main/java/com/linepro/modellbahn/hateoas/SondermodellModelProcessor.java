package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_SONDERMODELL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_SONDERMODELL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_SONDERMODELL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_SONDERMODELL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_SONDERMODELL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.SondermodellModel;

@Lazy
@Component(PREFIX + "SondermodellModelProcessor")
public class SondermodellModelProcessor extends NamedModelProcessor<SondermodellModel> implements RepresentationModelProcessor<SondermodellModel> {

    @Autowired
    public SondermodellModelProcessor() {
        super(
            ADD_SONDERMODELL, 
            GET_SONDERMODELL, 
            UPDATE_SONDERMODELL, 
            DELETE_SONDERMODELL, 
            SEARCH_SONDERMODELL
            );
    }
}
