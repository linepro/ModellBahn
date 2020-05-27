package com.linepro.modellbahn.hateoas;

import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.SoftDelete;
import com.linepro.modellbahn.model.ZugConsistModel;

@Lazy
@Component
public class ZugConsistModelProcessor extends ModelProcessorImpl<ZugConsistModel> implements RepresentationModelProcessor<ZugConsistModel> {

    private static final String ZUG = "{" + ApiNames.ZUG + "}";
    private static final String POSITION = "{" + ApiNames.POSITION + "}";

    @Autowired
    public ZugConsistModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { ZUG, ((ZugConsistModel) m).getZug() }, 
            { POSITION, String.valueOf(((ZugConsistModel) m).getPosition()) } 
            }),
                        new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_CONSIST),
                        new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_CONSIST, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted())));
    }
}
