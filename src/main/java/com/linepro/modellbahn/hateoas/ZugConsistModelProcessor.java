package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Collections;
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
import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.SoftDelete;
import com.linepro.modellbahn.model.ZugConsistModel;

@Lazy
@Component(PREFIX + "ZugConsistModelProcessor")
public class ZugConsistModelProcessor extends ModelProcessorImpl<ZugConsistModel> implements RepresentationModelProcessor<ZugConsistModel> {

    private static final String NAME = "{" + ApiNames.NAMEN + "}";
    private static final String POSITION = "{" + ApiNames.POSITION + "}";

    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { NAME, ((ZugConsistModel) m).getZug() }, 
        { POSITION, String.valueOf(((ZugConsistModel) m).getPosition()) } 
        });

    @Autowired
    public ZugConsistModelProcessor() {
        super(
            new LinkTemplateImpl(ApiRels.PARENT, ApiPaths.GET_ZUG, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_CONSIST, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_CONSIST, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted())),
            new LinkTemplateImpl(ApiNames.BAHNVERWALTUNG, ApiPaths.GET_BAHNVERWALTUNG, 
                    (m) -> Collections.singletonMap(NAME, ((ZugConsistModel) m).getBahnverwaltung())),
            new LinkTemplateImpl(ApiNames.GATTUNG, ApiPaths.GET_GATTUNG,  
                    (m) -> Collections.singletonMap(NAME, ((ZugConsistModel) m).getGattung())),
            new LinkTemplateImpl(ApiNames.ARTIKEL, ApiPaths.GET_ARTIKEL,  
                            (m) -> Collections.singletonMap("{" + ApiNames.ARTIKEL_ID + "}", ((ZugConsistModel) m).getArtikelId()))
            );
    }
}
