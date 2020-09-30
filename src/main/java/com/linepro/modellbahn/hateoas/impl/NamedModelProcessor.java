package com.linepro.modellbahn.hateoas.impl;

import static com.linepro.modellbahn.controller.impl.ApiRels.ADD;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.SEARCH;
import static com.linepro.modellbahn.controller.impl.ApiRels.SELF;
import static com.linepro.modellbahn.controller.impl.ApiRels.UPDATE;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.hateoas.RepresentationModel;

import com.linepro.modellbahn.model.SoftDelete;

public class NamedModelProcessor<M extends RepresentationModel<M>> extends ModelProcessorImpl<M> {

    protected static final FieldsExtractor EXTRACTOR = new NamedFieldExtractor();

    public NamedModelProcessor(String add, String self, String update, String delete, String search, LinkTemplate...additional) {
        super(
                new LinkTemplateImpl(SEARCH, search, EXTRACTOR),
                new LinkTemplateImpl(ADD, add, EXTRACTOR),
                new LinkTemplateImpl(SELF, self, EXTRACTOR),
                new LinkTemplateImpl(UPDATE, update, EXTRACTOR),
                new LinkTemplateImpl(DELETE, delete, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted()))
            );

        addLinks(additional);
    }
}
