package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ZUG_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_ZUG_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_ZUG_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_ZUG_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_ZUG_TYP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.ZugTypModel;

@Lazy
@Component(PREFIX + "ZugTypModelProcessor")
public class ZugTypModelProcessor extends NamedModelProcessor<ZugTypModel> implements RepresentationModelProcessor<ZugTypModel> {

    @Autowired
    public ZugTypModelProcessor() {
        super(
            ADD_ZUG_TYP, 
            GET_ZUG_TYP, 
            UPDATE_ZUG_TYP, 
            DELETE_ZUG_TYP, 
            SEARCH_ZUG_TYP
            );
    }
}
