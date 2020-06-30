package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.ZugModel;

@Lazy
@Component(PREFIX + "ZugModelProcessor")
public class ZugModelProcessor extends NamedModelProcessor<ZugModel> implements RepresentationModelProcessor<ZugModel> {

    public ZugModelProcessor() {
        super(
            ApiPaths.ADD_ZUG, 
            ApiPaths.GET_ZUG, 
            ApiPaths.UPDATE_ZUG, 
            ApiPaths.DELETE_ZUG, 
            ApiPaths.SEARCH_ZUG,
            new LinkTemplateImpl(ApiRels.CONSIST, ApiPaths.ADD_CONSIST, EXTRACTOR)
            );
    }
}
