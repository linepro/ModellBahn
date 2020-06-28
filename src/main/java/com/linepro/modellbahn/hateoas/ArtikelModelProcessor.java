package com.linepro.modellbahn.hateoas;

import java.util.Collections;
import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.ApiRels;
import com.linepro.modellbahn.hateoas.impl.FieldsExtractor;
import com.linepro.modellbahn.hateoas.impl.LinkTemplateImpl;
import com.linepro.modellbahn.hateoas.impl.ModelProcessorImpl;
import com.linepro.modellbahn.model.ArtikelModel;

@Lazy
@Component("ArtikelModelProcessor")
public class ArtikelModelProcessor extends ModelProcessorImpl<ArtikelModel> implements RepresentationModelProcessor<ArtikelModel> {

    private static final String ARTIKEL_ID = "{" + ApiNames.ARTIKEL_ID + "}";
    private static final String DECODER_IID = "{" + ApiNames.DECODER_ID + "}";
    private static final String NAME = "{" + ApiNames.NAMEN + "}";

    private static final FieldsExtractor EXTRACTOR = (m) -> Collections.singletonMap(ARTIKEL_ID, ((ArtikelModel) m).getArtikelId());
    
    @Autowired
    public ArtikelModelProcessor() {
        super(
            new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.SELF, ApiPaths.GET_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.UPDATE, ApiPaths.UPDATE_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.DELETE, ApiPaths.DELETE_ARTIKEL, EXTRACTOR, (m) -> BooleanUtils.isFalse(((ArtikelModel) m).getDeleted())),
            new LinkTemplateImpl(ApiRels.SEARCH, ApiPaths.SEARCH_ARTIKEL, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.IMAGE, ApiPaths.ADD_ARTIKEL_ABBILDUNG, EXTRACTOR),
            new LinkTemplateImpl(ApiRels.ADD, ApiPaths.ADD_ANDERUNG, EXTRACTOR),
            new LinkTemplateImpl(ApiNames.PRODUKT, ApiPaths.GET_PRODUKT, (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
                { "{" + ApiNames.HERSTELLER + "}", ((ArtikelModel) m).getHersteller() }, 
                { "{" + ApiNames.BESTELL_NR + "}", ((ArtikelModel) m).getBestellNr() } 
                })),
            new LinkTemplateImpl(NAME, ApiPaths.GET_KATEGORIE, (m) -> Collections.singletonMap(ApiNames.KATEGORIE, ((ArtikelModel) m).getKategorie())),
            new LinkTemplateImpl(ApiNames.UNTER_KATEGORIE, ApiPaths.GET_UNTER_KATEGORIE, (m) -> MapUtils.putAll(new HashMap<String,Object>(), new String[][] { 
                { "{" + ApiNames.KATEGORIE + "}", ((ArtikelModel) m).getKategorie() }, 
                { NAME, ((ArtikelModel) m).getUnterKategorie() } 
                })),
            new LinkTemplateImpl(ApiNames.MASSSTAB, ApiPaths.GET_MASSSTAB, 
                            (m) -> Collections.singletonMap(NAME, ((ArtikelModel) m).getMassstab()),
                            (m) -> ((ArtikelModel) m).getMassstab() != null
                            ),
            new LinkTemplateImpl(ApiNames.SPURWEITE, ApiPaths.GET_SPURWEITE,  
                            (m) -> Collections.singletonMap(NAME, ((ArtikelModel) m).getSpurweite()),
                            (m) -> ((ArtikelModel) m).getSpurweite() != null
                            ),
            new LinkTemplateImpl(ApiNames.EPOCH, ApiPaths.GET_EPOCH,  
                            (m) -> Collections.singletonMap(NAME, ((ArtikelModel) m).getEpoch()),
                            (m) -> ((ArtikelModel) m).getEpoch() != null
                            ),
            new LinkTemplateImpl(ApiNames.BAHNVERWALTUNG, ApiPaths.GET_BAHNVERWALTUNG,  
                            (m) -> Collections.singletonMap(NAME, ((ArtikelModel) m).getBahnverwaltung()),
                            (m) -> ((ArtikelModel) m).getBahnverwaltung() != null
                            ),
            new LinkTemplateImpl(ApiNames.GATTUNG, ApiPaths.GET_GATTUNG,  
                            (m) -> Collections.singletonMap(NAME, ((ArtikelModel) m).getGattung()),
                            (m) -> ((ArtikelModel) m).getGattung() != null
                            ),
            new LinkTemplateImpl(ApiNames.ACHSFOLG, ApiPaths.GET_ACHSFOLG,  
                            (m) -> Collections.singletonMap(NAME, ((ArtikelModel) m).getAchsfolg()),
                            (m) -> ((ArtikelModel) m).getAchsfolg() != null
                            ),
            new LinkTemplateImpl(ApiNames.SONDERMODELL, ApiPaths.GET_SONDERMODELL,  
                            (m) -> Collections.singletonMap(NAME, ((ArtikelModel) m).getSondermodell()),
                            (m) -> ((ArtikelModel) m).getSondermodell() != null
                            ),
            new LinkTemplateImpl(ApiNames.AUFBAU, ApiPaths.GET_AUFBAU,  
                            (m) -> Collections.singletonMap(NAME, ((ArtikelModel) m).getAufbau()),
                            (m) -> ((ArtikelModel) m).getAufbau() != null
                            ),
            new LinkTemplateImpl(ApiNames.LICHT, ApiPaths.GET_LICHT,  
                            (m) -> Collections.singletonMap(NAME, ((ArtikelModel) m).getLicht()),
                            (m) -> ((ArtikelModel) m).getLicht() != null
                            ),
            new LinkTemplateImpl(ApiNames.KUPPLUNG, ApiPaths.GET_KUPPLUNG,  
                            (m) -> Collections.singletonMap(NAME, ((ArtikelModel) m).getKupplung()),
                            (m) -> ((ArtikelModel) m).getKupplung() != null
                            ),
            new LinkTemplateImpl(ApiNames.STEUERUNG, ApiPaths.GET_STEUERUNG, 
                            (m) -> Collections.singletonMap(ApiNames.STEUERUNG, ((ArtikelModel) m).getSteuerung()),
                            (m) -> ((ArtikelModel) m).getSteuerung() != null
                            ),
            new LinkTemplateImpl(ApiNames.DECODER, ApiPaths.GET_DECODER,  
                            (m) -> Collections.singletonMap(DECODER_IID, ((ArtikelModel) m).getDecoder()),
                            (m) -> ((ArtikelModel) m).getDecoder() != null
                            ),
            new LinkTemplateImpl(ApiNames.MOTOR_TYP, ApiPaths.GET_MOTOR_TYP,  
                            (m) -> Collections.singletonMap(NAME, ((ArtikelModel) m).getMotorTyp()),
                            (m) -> ((ArtikelModel) m).getMotorTyp() != null
                            )
            );
    }
}
