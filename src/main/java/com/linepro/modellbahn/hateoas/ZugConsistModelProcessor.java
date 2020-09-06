package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.NAMEN;
import static com.linepro.modellbahn.controller.impl.ApiNames.POSITION;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_CONSIST;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_ZUG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_CONSIST;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.PARENT;
import static com.linepro.modellbahn.controller.impl.ApiRels.UPDATE;

import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.SoftDelete;
import com.linepro.modellbahn.model.ZugConsistModel;

@Lazy
@Component(PREFIX + "ZugConsistModelProcessor")
public class ZugConsistModelProcessor extends ModelProcessorImpl<ZugConsistModel> implements RepresentationModelProcessor<ZugConsistModel> {

    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { NAMEN, ((ZugConsistModel) m).getZug() }, 
        { POSITION, String.valueOf(((ZugConsistModel) m).getPosition()) } 
        });

    @Autowired
    public ZugConsistModelProcessor() {
        super(
            new LinkTemplateImpl(PARENT, GET_ZUG, EXTRACTOR),
            new LinkTemplateImpl(UPDATE, UPDATE_CONSIST, EXTRACTOR),
            new LinkTemplateImpl(DELETE, DELETE_CONSIST, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted()))
            );
    }
}
