package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.AntriebModel;

@Lazy
@Component("AntriebModelProcessor")
public class AntriebModelProcessor extends NamedModelProcessor<AntriebModel> implements RepresentationModelProcessor<AntriebModel> {

    @Autowired
    public AntriebModelProcessor() {
        super(
            ApiPaths.ADD_ANTRIEB,
            ApiPaths.GET_ANTRIEB,
            ApiPaths.UPDATE_ANTRIEB,
            ApiPaths.DELETE_ANTRIEB,
            ApiPaths.SEARCH_ANTRIEB
            );
    }
}
