package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_CONSIST;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ZUG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_ZUG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_ZUG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_ZUG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_ZUG;
import static com.linepro.modellbahn.controller.impl.ApiRels.CONSIST;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.ZugModel;

@Lazy
@Component(PREFIX + "ZugModelProcessor")
public class ZugModelProcessor extends NamedModelProcessor<ZugModel> implements RepresentationModelProcessor<ZugModel> {

    private ZugConsistModelProcessor consistProcessor;

    @Autowired
    public ZugModelProcessor(ZugConsistModelProcessor consistProcessor) {
        super(
            ADD_ZUG, 
            GET_ZUG, 
            UPDATE_ZUG, 
            DELETE_ZUG, 
            SEARCH_ZUG,
            new LinkTemplateImpl(CONSIST, ADD_CONSIST, EXTRACTOR)
            );

        this.consistProcessor = consistProcessor;
    }

    @Override
    public ZugModel process(ZugModel model) {
        if (!CollectionUtils.isEmpty(model.getConsist())) {
            model.getConsist()
                 .forEach(c -> consistProcessor.process(c));
        }

        return super.process(model);
    }
}
