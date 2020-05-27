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
import com.linepro.modellbahn.model.UnterKategorieModel;

@Lazy
@Component
public class UnterKategorieModelProcessor extends ModelProcessorImpl<UnterKategorieModel> implements RepresentationModelProcessor<UnterKategorieModel> {

    private static final String KATEGORIE = "{" + ApiNames.KATEGORIE + "}";
    private static final String UNTER_KATEGORIE = "{" + ApiNames.UNTER_KATEGORIE + "}";

    @Autowired
    public UnterKategorieModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { KATEGORIE, ((UnterKategorieModel) m).getKategorie() }, 
            { UNTER_KATEGORIE, ((UnterKategorieModel) m).getName() } 
            }),
            new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_UNTER_KATEGORIE),
            new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_UNTER_KATEGORIE, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted())),
            new LinkTemplateImpl(ApiRels.SEARCH, ApiPaths.SEARCH_UNTER_KATEGORIE));
    }
}
