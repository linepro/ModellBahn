package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.ZugTypModel;

@Lazy
@Component(PREFIX + "ZugTypModelProcessor")
public class ZugTypModelProcessor extends NamedModelProcessor<ZugTypModel> implements RepresentationModelProcessor<ZugTypModel> {

    @Autowired
    public ZugTypModelProcessor() {
        super(
            ApiPaths.ADD_ZUG_TYP, 
            ApiPaths.GET_ZUG_TYP, 
            ApiPaths.UPDATE_ZUG_TYP, 
            ApiPaths.DELETE_ZUG_TYP, 
            ApiPaths.SEARCH_ZUG_TYP
            );
    }
}
