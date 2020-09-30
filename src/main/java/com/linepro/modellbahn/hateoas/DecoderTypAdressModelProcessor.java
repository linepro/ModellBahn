package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiNames.BESTELL_NR;
import static com.linepro.modellbahn.controller.impl.ApiNames.HERSTELLER;
import static com.linepro.modellbahn.controller.impl.ApiNames.INDEX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.DELETE_DECODER_TYP_ADRESS;
import static com.linepro.modellbahn.controller.impl.ApiPaths.GET_DECODER_TYP;
import static com.linepro.modellbahn.controller.impl.ApiPaths.UPDATE_DECODER_TYP_ADRESS;
import static com.linepro.modellbahn.controller.impl.ApiRels.DELETE;
import static com.linepro.modellbahn.controller.impl.ApiRels.PARENT;
import static com.linepro.modellbahn.controller.impl.ApiRels.UPDATE;

import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.DecoderTypAdressModel;
import com.linepro.modellbahn.model.SoftDelete;

@Lazy
@Component(PREFIX + "DecoderTypAdressModelProcessor")
public class DecoderTypAdressModelProcessor extends ModelProcessorImpl<DecoderTypAdressModel> implements RepresentationModelProcessor<DecoderTypAdressModel> {

    private static FieldsExtractor EXTRACTOR = (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
        { HERSTELLER, ((DecoderTypAdressModel) m).getHersteller() }, 
        { BESTELL_NR, ((DecoderTypAdressModel) m).getBestellNr() }, 
        { INDEX, String.valueOf(((DecoderTypAdressModel) m).getIndex()) } 
        });

    @Autowired
    public DecoderTypAdressModelProcessor() {
        super(
            new LinkTemplateImpl(PARENT, GET_DECODER_TYP, EXTRACTOR),
            new LinkTemplateImpl(UPDATE, UPDATE_DECODER_TYP_ADRESS, EXTRACTOR),
            new LinkTemplateImpl(DELETE, DELETE_DECODER_TYP_ADRESS, EXTRACTOR, (m) -> BooleanUtils.isFalse(((SoftDelete) m).getDeleted()))
            );
    }
}
