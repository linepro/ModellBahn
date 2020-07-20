package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_CONSIST;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ZUG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_ZUG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_ZUG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_ZUG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_ZUG;

import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.ZugModel;

@Lazy
@Component(PREFIX + "ZugModelProcessor")
public class ZugModelProcessor extends NamedModelProcessor<ZugModel> implements RepresentationModelProcessor<ZugModel> {

    public ZugModelProcessor() {
        super(
            ADD_ZUG, 
            GET_ZUG, 
            UPDATE_ZUG, 
            DELETE_ZUG, 
            SEARCH_ZUG,
            new LinkTemplateImpl(ApiRels.CONSIST, ADD_CONSIST, EXTRACTOR)
            );
    }
}
