package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiNames.UNTER_KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_UNTER_KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_KATEGORIE;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_UNTER_KATEGORIE;
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
import com.linepro.modellbahn.model.UnterKategorieModel;

@Lazy
@Component(PREFIX + "UnterKategorieModelProcessor")
public class UnterKategorieModelProcessor extends ModelProcessorImpl<UnterKategorieModel> implements RepresentationModelProcessor<UnterKategorieModel> {

    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { KATEGORIE, ((UnterKategorieModel) m).getKategorie() }, 
        { UNTER_KATEGORIE, ((UnterKategorieModel) m).getName() } 
        });
    
    @Autowired
    public UnterKategorieModelProcessor() {
        super(
            new LinkTemplateImpl(UPDATE, UPDATE_UNTER_KATEGORIE, EXTRACTOR),
            new LinkTemplateImpl(DELETE, DELETE_UNTER_KATEGORIE, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted())),
            new LinkTemplateImpl(PARENT, GET_KATEGORIE, EXTRACTOR));
    }
}
