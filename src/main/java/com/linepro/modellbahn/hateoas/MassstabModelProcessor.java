package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_MASSSTAB;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_MASSSTAB;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_MASSSTAB;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_MASSSTAB;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_MASSSTAB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.MassstabModel;

@Lazy
@Component(PREFIX + "MassstabModelProcessor")
public class MassstabModelProcessor extends NamedModelProcessor<MassstabModel> implements RepresentationModelProcessor<MassstabModel> {

    @Autowired
    public MassstabModelProcessor() {
        super(
            ADD_MASSSTAB, 
            GET_MASSSTAB,
            UPDATE_MASSSTAB,
            DELETE_MASSSTAB,
            SEARCH_MASSSTAB
            );
    }
}
