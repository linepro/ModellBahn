package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.GATTUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_VORBILD;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_VORBILD_ABBILDUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_VORBILD;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_VORBILD;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_VORBILD;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_VORBILD;
import static com.linepro.modellbahn.controller.impl.ApiRels.ADD;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.IMAGE;
import static com.linepro.modellbahn.controller.impl.ApiRels.SEARCH;
import static com.linepro.modellbahn.controller.impl.ApiRels.SELF;
import static com.linepro.modellbahn.controller.impl.ApiRels.UPDATE;

import java.util.Collections;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.VorbildModel;

@Lazy
@Component(PREFIX + "VorbildModelProcessor")
public class VorbildModelProcessor extends ModelProcessorImpl<VorbildModel> implements RepresentationModelProcessor<VorbildModel> {

    private static final FieldsExtractor EXTRACTOR = (m) -> Collections.singletonMap(GATTUNG, ((VorbildModel) m).getGattung());

    @Autowired
    public VorbildModelProcessor() {
        super(
            new LinkTemplateImpl(ADD, ADD_VORBILD, EXTRACTOR),
            new LinkTemplateImpl(SELF, GET_VORBILD, EXTRACTOR),
            new LinkTemplateImpl(UPDATE, UPDATE_VORBILD, EXTRACTOR),
            new LinkTemplateImpl(DELETE, DELETE_VORBILD, EXTRACTOR, (m) -> BooleanUtils.isFalse(((VorbildModel) m).getDeleted())),
            new LinkTemplateImpl(SEARCH, SEARCH_VORBILD, EXTRACTOR),
            new LinkTemplateImpl(IMAGE, ADD_VORBILD_ABBILDUNG, EXTRACTOR)
            );
    }
}
