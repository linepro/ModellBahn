package com.linepro.modellbahn.hateoas.impl;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.hateoas.RepresentationModel;

import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.model.SoftDelete;

public class NamedModelProcessor<M extends RepresentationModel<M>> extends ModelProcessorImpl<M> {

    protected static final FieldsExtractor EXTRACTOR = new NamedFieldExtractor();

    public NamedModelProcessor(String add, String self, String update, String delete, String search, LinkTemplate...additional) {
        super(
                new LinkTemplateImpl(ApiRels.ADD, add, EXTRACTOR),
                new LinkTemplateImpl(ApiRels.SELF, self, EXTRACTOR),
                new LinkTemplateImpl(ApiRels.UPDATE, update, EXTRACTOR),
                new LinkTemplateImpl(ApiRels.DELETE, delete, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted())),
                new LinkTemplateImpl(ApiRels.SEARCH, search, EXTRACTOR)
            );
    }
}
