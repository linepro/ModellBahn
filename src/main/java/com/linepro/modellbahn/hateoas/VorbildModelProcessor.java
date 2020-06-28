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
import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.VorbildModel;

@Lazy
@Component("VorbildModelProcessor")
public class VorbildModelProcessor extends ModelProcessorImpl<VorbildModel> implements RepresentationModelProcessor<VorbildModel> {

    private static final String GATTUNG = "{" + ApiNames.GATTUNG + "}";
    private static final FieldsExtractor EXTRACTOR = (m) -> Collections.singletonMap(GATTUNG, ((VorbildModel) m).getGattung());

    @Autowired
    public VorbildModelProcessor() {
        super(
            new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_VORBILD, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.SELF, ApiPaths.GET_VORBILD, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_VORBILD, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_VORBILD, EXTRACTOR, (m) -> BooleanUtils.isFalse(((VorbildModel) m).getDeleted())),
            new LinkTemplateImpl(ApiRels.SEARCH, ApiPaths.SEARCH_VORBILD, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.IMAGE, ApiPaths.ADD_VORBILD_ABBILDUNG, EXTRACTOR)
            );
    }
}
