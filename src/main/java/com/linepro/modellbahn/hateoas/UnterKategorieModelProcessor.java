package com.linepro.modellbahn.hateoas;

import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.UnterKategorieModel;

@Lazy
@Component
public class UnterKategorieModelProcessor extends ModelProcessorImpl<UnterKategorieModel> implements RepresentationModelProcessor<UnterKategorieModel> {

    @Autowired
    public UnterKategorieModelProcessor() {
        super((m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
            { "{" + ApiNames.KATEGORIE + "}", ((UnterKategorieModel) m).getKategorie() }, 
            { "{" + ApiNames.UNTER_KATEGORIE + "}", ((UnterKategorieModel) m).getName() } 
            }),
            new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_UNTER_KATEGORIE),
            new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_UNTER_KATEGORIE),
            new LinkTemplateImpl(ApiRels.SEARCH, ApiPaths.SEARCH_UNTER_KATEGORIE));
    }
}
