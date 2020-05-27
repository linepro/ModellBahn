package com.linepro.modellbahn.hateoas;

import java.util.Collections;

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
import com.linepro.modellbahn.model.VorbildModel;

@Lazy
@Component
public class VorbildModelProcessor extends ModelProcessorImpl<VorbildModel> implements RepresentationModelProcessor<VorbildModel> {

    private static final String GATTUNG = "{" + ApiNames.GATTUNG + "}";

    @Autowired
    public VorbildModelProcessor() {
        super((m) -> Collections.singletonMap(GATTUNG, ((VorbildModel) m).getGattung()),
            new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_VORBILD),
            new LinkTemplateImpl(ApiRels.SELF, ApiPaths.GET_VORBILD),
            new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_VORBILD),
            new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_VORBILD, (m) -> BooleanUtils.isFalse(((VorbildModel) m).getDeleted())),
            new LinkTemplateImpl(ApiRels.SEARCH, ApiPaths.SEARCH_VORBILD),
            new LinkTemplateImpl(ApiRels.ADD_IMAGE, ApiPaths.ADD_VORBILD_ABBILDUNG),
            new LinkTemplateImpl(ApiRels.REMOVE_IMAGE, ApiPaths.DELETE_VORBILD_ABBILDUNG, (m) -> (((VorbildModel) m).getAbbildung()) != null));
    }
}
