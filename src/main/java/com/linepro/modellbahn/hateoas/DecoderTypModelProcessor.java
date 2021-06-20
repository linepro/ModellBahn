package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.BESTELL_NR;
import static com.linepro.modellbahn.controller.impl.ApiNames.HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_DECODER_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_DECODER_TYP_ADRESS;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_DECODER_TYP_ANLEITUNGEN;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_DECODER_TYP_CV;
import static com.linepro.modellbahn.controller.impl.ApiPaths.ADD_DECODER_TYP_FUNKTION;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_DECODER_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_DECODER_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.SEARCH_DECODER_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_DECODER_TYP;
import static com.linepro.modellbahn.controller.impl.ApiRels.ADD;
import static com.linepro.modellbahn.controller.impl.ApiRels.ADRESSEN;
import static com.linepro.modellbahn.controller.impl.ApiRels.ANLEITUNGEN;
import static com.linepro.modellbahn.controller.impl.ApiRels.CVS;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.FUNKTIONEN;
import static com.linepro.modellbahn.controller.impl.ApiRels.SEARCH;
import static com.linepro.modellbahn.controller.impl.ApiRels.SELF;
import static com.linepro.modellbahn.controller.impl.ApiRels.UPDATE;

import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.model.SoftDelete;

@Lazy
@Component(PREFIX + "DecoderTypModelProcessor")
public class DecoderTypModelProcessor extends ModelProcessorImpl<DecoderTypModel> implements RepresentationModelProcessor<DecoderTypModel> {

    private static final FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String, Object>(), new String[][] {
                    { HERSTELLER, ((DecoderTypModel) m).getHersteller() },
                    { BESTELL_NR, ((DecoderTypModel) m).getBestellNr() },
                    });

    private final DecoderTypAdressModelProcessor adressProcessor;

    private final DecoderTypCvModelProcessor cvProcessor;

    private final DecoderTypFunktionModelProcessor funktionProcessor;

    @Autowired
    public DecoderTypModelProcessor(DecoderTypAdressModelProcessor adressProcessor, DecoderTypCvModelProcessor cvProcessor,
                    DecoderTypFunktionModelProcessor funktionProcessor) {
        super(new LinkTemplateImpl(ADD, ADD_DECODER_TYP, EXTRACTOR),
              new LinkTemplateImpl(SELF, GET_DECODER_TYP, EXTRACTOR),
              new LinkTemplateImpl(SEARCH, SEARCH_DECODER_TYP, EXTRACTOR),
              new LinkTemplateImpl(DELETE, DELETE_DECODER_TYP, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted())),
              new LinkTemplateImpl(UPDATE, UPDATE_DECODER_TYP, EXTRACTOR),
              new LinkTemplateImpl(ANLEITUNGEN, ADD_DECODER_TYP_ANLEITUNGEN, EXTRACTOR),
              new LinkTemplateImpl(ADRESSEN, ADD_DECODER_TYP_ADRESS, EXTRACTOR),
              new LinkTemplateImpl(CVS, ADD_DECODER_TYP_CV, EXTRACTOR),
              new LinkTemplateImpl(FUNKTIONEN, ADD_DECODER_TYP_FUNKTION, EXTRACTOR));

        this.adressProcessor = adressProcessor;

        this.cvProcessor = cvProcessor;

        this.funktionProcessor = funktionProcessor;
    }

    @Override
    public DecoderTypModel process(DecoderTypModel model) {
        if (!CollectionUtils.isEmpty(model.getAdressen())) {
            model.getAdressen().forEach(a -> adressProcessor.process(a));
        }

        if (!CollectionUtils.isEmpty(model.getCvs())) {
            model.getCvs().forEach(c -> cvProcessor.process(c));
        }

        if (!CollectionUtils.isEmpty(model.getFunktionen())) {
            model.getFunktionen().forEach(f -> funktionProcessor.process(f));
        }

        return super.process(model);
    }
}
