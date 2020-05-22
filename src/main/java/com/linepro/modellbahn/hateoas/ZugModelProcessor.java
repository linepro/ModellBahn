package com.linepro.modellbahn.hateoas;

import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.ZugModel;

@Lazy
@Component
public class ZugModelProcessor extends NamedModelProcessor<ZugModel> implements RepresentationModelProcessor<ZugModel> {

    public ZugModelProcessor() {
        super(ApiPaths.ADD_ZUG, ApiPaths.GET_ZUG, ApiPaths.UPDATE_ZUG, ApiPaths.DELETE_ZUG, ApiPaths.SEARCH_ZUG,
                        new LinkTemplateImpl("add consist", ApiPaths.ADD_CONSIST));
    }
}
