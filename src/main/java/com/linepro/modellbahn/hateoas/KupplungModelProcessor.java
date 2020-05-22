package com.linepro.modellbahn.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.NamedModelProcessor;
import com.linepro.modellbahn.model.KupplungModel;

@Component
public class KupplungModelProcessor extends NamedModelProcessor<KupplungModel> implements RepresentationModelProcessor<KupplungModel> {

    @Autowired
    public KupplungModelProcessor() {
        super(ApiPaths.ADD_KUPPLUNG, ApiPaths.GET_KUPPLUNG, ApiPaths.UPDATE_KUPPLUNG, ApiPaths.DELETE_KUPPLUNG, ApiPaths.SEARCH_KUPPLUNG,
              new LinkTemplateImpl("add image", ApiPaths.ADD_KUPPLUNG_ABBILDUNG), new LinkTemplateImpl("remove image", ApiPaths.DELETE_KUPPLUNG_ABBILDUNG));
    }
}
