package com.linepro.modellbahn.hateoas;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.ArtikelModel;

@Lazy
@Component
public class ArtikelModelProcessor extends ModelProcessorImpl<ArtikelModel> implements RepresentationModelProcessor<ArtikelModel> {

    @Autowired
    public ArtikelModelProcessor() {
        super((m) -> Collections.singletonMap("{" + ApiNames.ARTIKEL_ID + "}", ((ArtikelModel) m).getArtikelId()),
                        new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_ARTIKEL),
                        new LinkTemplateImpl(ApiRels.SELF, ApiPaths.GET_ARTIKEL),
                        new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_ARTIKEL),
                        new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_ARTIKEL),
                        new LinkTemplateImpl(ApiRels.SEARCH, ApiPaths.SEARCH_ARTIKEL),
                        new LinkTemplateImpl(ApiRels.ADD_IMAGE, ApiPaths.ADD_ARTIKEL_ABBILDUNG),
                        new LinkTemplateImpl(ApiRels.REMOVE_IMAGE, ApiPaths.DELETE_ARTIKEL_ABBILDUNG),
                        new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_ANDERUNG));
    }
}
