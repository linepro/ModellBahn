package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ANTRIEB;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_ANTRIEB;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_ANTRIEB;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_ANTRIEB;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_ANTRIEB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.AntriebModel;

@Lazy
@Component(PREFIX + "AntriebModelProcessor")
public class AntriebModelProcessor extends NamedModelProcessor<AntriebModel> implements RepresentationModelProcessor<AntriebModel> {

    @Autowired
    public AntriebModelProcessor() {
        super(
            ADD_ANTRIEB,
            GET_ANTRIEB,
            UPDATE_ANTRIEB,
            DELETE_ANTRIEB,
            SEARCH_ANTRIEB
            );
    }
}
