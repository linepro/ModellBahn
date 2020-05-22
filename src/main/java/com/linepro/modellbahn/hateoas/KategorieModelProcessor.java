package com.linepro.modellbahn.hateoas;

import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.KategorieModel;

@Lazy
@Component
public class KategorieModelProcessor extends NamedModelProcessor<KategorieModel> implements RepresentationModelProcessor<KategorieModel> {

    public KategorieModelProcessor() {
        super(ApiPaths.ADD_KATEGORIE, ApiPaths.GET_KATEGORIE, ApiPaths.UPDATE_KATEGORIE, ApiPaths.DELETE_KATEGORIE, ApiPaths.SEARCH_KATEGORIE,
              new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_UNTER_KATEGORIE));
    }
}
