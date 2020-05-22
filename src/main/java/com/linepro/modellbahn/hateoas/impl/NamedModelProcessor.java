package com.linepro.modellbahn.hateoas.impl;

import org.springframework.hateoas.RepresentationModel;

import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.model.SoftDelete;

public class NamedModelProcessor<M extends RepresentationModel<M>> extends ModelProcessorImpl<M> {

    public NamedModelProcessor(String add, String self, String update, String delete, String search, LinkTemplate...additional) {
        super(
                new NamedFieldExtractor(), 
                new LinkTemplateImpl(ApiRels.ADD, add),
                new LinkTemplateImpl(ApiRels.SELF, self),
                new LinkTemplateImpl(ApiRels.UPDATE, update),
                new LinkTemplateImpl(ApiRels.DELETE, delete, (m) -> !((SoftDelete) m).getDeleted()),
                new LinkTemplateImpl(ApiRels.SEARCH, search)
            );
    }
}
