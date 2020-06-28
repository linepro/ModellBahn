package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.MassstabModel;

@Lazy
@Component("MassstabModelProcessor")
public class MassstabModelProcessor extends NamedModelProcessor<MassstabModel> implements RepresentationModelProcessor<MassstabModel> {

    @Autowired
    public MassstabModelProcessor() {
        super(
            ApiPaths.ADD_MASSSTAB, 
            ApiPaths.GET_MASSSTAB,
            ApiPaths.UPDATE_MASSSTAB,
            ApiPaths.DELETE_MASSSTAB,
            ApiPaths.SEARCH_MASSSTAB
            );
    }
}
