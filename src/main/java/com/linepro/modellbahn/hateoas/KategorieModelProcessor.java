package com.linepro.modellbahn.hateoas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplate;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.KategorieModel;

@Lazy
@Component
public class KategorieModelProcessor extends NamedModelProcessor<KategorieModel> implements RepresentationModelProcessor<KategorieModel> {

    private static final String KATEGORIE = "{" + ApiNames.KATEGORIE + "}";
    private static final String UNTER_KATEGORIE = "{" + ApiNames.UNTER_KATEGORIE + "}";
    private static final LinkTemplate UNTER_KATEGORIE_SELF = new LinkTemplateImpl(ApiRels.SELF, ApiPaths.GET_UNTER_KATEGORIE);
    
    public KategorieModelProcessor() {
        super(ApiPaths.ADD_KATEGORIE, ApiPaths.GET_KATEGORIE, ApiPaths.UPDATE_KATEGORIE, ApiPaths.DELETE_KATEGORIE, ApiPaths.SEARCH_KATEGORIE,
              new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_UNTER_KATEGORIE));
    }

    @Override
    public KategorieModel process(KategorieModel model) {
        Map<String,Object> paths = new HashMap<>(); 
        paths.put(KATEGORIE, model.getName()); 
        paths.put(UNTER_KATEGORIE, ""); 
        model.getUnterKategorien().forEach(u -> { paths.put(UNTER_KATEGORIE, u.getName()); UNTER_KATEGORIE_SELF.apply(u, paths); });
        return super.process(model);
    }
}
