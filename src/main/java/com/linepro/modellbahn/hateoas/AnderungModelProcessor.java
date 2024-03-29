package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.ANDERUNG_ID;
import static com.linepro.modellbahn.controller.impl.ApiNames.ARTIKEL_ID;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_ANDERUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_ANDERUNG;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_ARTIKEL;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_ANDERUNG;
import static com.linepro.modellbahn.controller.impl.ApiRels.ADD;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.PARENT;
import static com.linepro.modellbahn.controller.impl.ApiRels.UPDATE;

import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.AnderungModel;

@Lazy
@Component(PREFIX + "AnderungModelProcessor")
public class AnderungModelProcessor extends ModelProcessorImpl<AnderungModel> implements RepresentationModelProcessor<AnderungModel> {

    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { ARTIKEL_ID, ((AnderungModel) m).getArtikelId() }, 
        { ANDERUNG_ID, String.valueOf(((AnderungModel) m).getAnderungId()) } 
        });

    @Autowired
    public AnderungModelProcessor() {
        super(
            new LinkTemplateImpl(PARENT, GET_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(ADD, ADD_ANDERUNG, EXTRACTOR),
            new LinkTemplateImpl(UPDATE, UPDATE_ANDERUNG, EXTRACTOR),
            new LinkTemplateImpl(DELETE, DELETE_ANDERUNG, EXTRACTOR, (m) -> !(((AnderungModel) m).getDeleted()))
            );
    }
}
