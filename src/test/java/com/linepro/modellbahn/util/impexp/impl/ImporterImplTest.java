package com.linepro.modellbahn.util.impexp.impl;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.LENIENT;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.util.ReflectionTestUtils;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.io.FileStore;
import com.linepro.modellbahn.io.FileStoreImpl;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.repository.lookup.Lookup;
import com.linepro.modellbahn.request.DecoderTypRequest;
import com.linepro.modellbahn.util.impexp.Importer;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class ImporterImplTest {

    private static final int ADRESS = 80;
    private static final AdressTyp ADRESS_TYP = AdressTyp.DIGITAL;
    private static final String ANLEITUNGEN = "D:\\Users\\ESU\\66666_betrieb.pdf";
    private static final String BESTELL_NR = "66666";
    private static final String BEZEICHNUNG = "LokPilot Test";
    private static final Boolean DELETED = false;
    private static final Integer FAHRSTUFE = 128;
    private static final String HERSTELLER = "ESU";
    private static final BigDecimal I_MAX = new BigDecimal("1.10");
    private static final Konfiguration KONFIGURATION = Konfiguration.CV;
    private static final boolean MOTOR = true;
    private static final int OUTPUTS = 4;
    private static final String PROTOKOLL = "MFX";
    private static final int SERVOS = 2;
    private static final int SPAN = 1;
    private static final Stecker STECKER = Stecker.NEM652;
    private static final boolean SOUND = false;

    private static final String FILE_STORE_ROOT = "static";
    private static final String PATH = FILE_STORE_ROOT + "\\data\\" + ApiNames.DECODER_TYP + "\\" + HERSTELLER + "\\" + BESTELL_NR + "\\" + "anleitungen.pdf";

    private static final Long ID = 1L;

    private static final Hersteller HS = Hersteller.builder().id(ID).name(HERSTELLER).build();
    private static final Protokoll PR = Protokoll.builder().id(2L).name(PROTOKOLL).build();
    
    private static final DecoderTyp ENTITY = DecoderTyp.builder()
                                                       .hersteller(HS)
                                                       .bezeichnung(BEZEICHNUNG)
                                                       .fahrstufe(FAHRSTUFE)
                                                       .iMax(I_MAX)
                                                       .protokoll(PR)
                                                       .adressTyp(ADRESS_TYP)
                                                       .span(SPAN)
                                                       .adress(ADRESS)
                                                       .fahrstufe(FAHRSTUFE)
                                                       .sound(SOUND)
                                                       .motor(MOTOR)
                                                       .outputs(OUTPUTS)
                                                       .servos(SERVOS)
                                                       .konfiguration(KONFIGURATION)
                                                       .stecker(Stecker.MTC21)
                                                       .anleitungen(Path.of(PATH))
                                                       .cvs(Collections.emptySet())
                                                       .funktionen(Collections.emptySet())
                                                       .deleted(DELETED)
                                                       .build();

    private static final DecoderTypRequest REQUEST = DecoderTypRequest.builder()
                                                                      .hersteller(HERSTELLER)
                                                                      .bestellNr(BESTELL_NR)
                                                                      .bezeichnung(BEZEICHNUNG)
                                                                      .iMax(I_MAX)
                                                                      .protokoll(PROTOKOLL)
                                                                      .fahrstufe(FAHRSTUFE)
                                                                      .adressTyp(ADRESS_TYP)
                                                                      .adress(ADRESS)
                                                                      .span(SPAN)
                                                                      .sound(SOUND)
                                                                      .motor(MOTOR)
                                                                      .outputs(OUTPUTS)
                                                                      .servos(SERVOS)
                                                                      .konfiguration(KONFIGURATION)
                                                                      .stecker(STECKER)
                                                                      .anleitungen(ANLEITUNGEN)
                                                                      .deleted(DELETED)
                                                                      .build();

    private final CsvSchemaGenerator generator = new CsvSchemaGenerator();

    @Mock
    private JpaRepository<DecoderTyp,Long> repository;

    @Mock
    private Mapper<DecoderTypRequest,DecoderTyp> mapper;  

    @Mock
    private Lookup<DecoderTyp, DecoderTypModel> lookup;

    private FileStore fileStore;

    private Importer importer;

    @Captor
    private ArgumentCaptor<DecoderTypRequest> requestCaptor;

    @Captor
    private ArgumentCaptor<DecoderTyp> entityCaptor;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @BeforeEach
    protected void setUp() throws Exception {
        fileStore = new FileStoreImpl();

        ReflectionTestUtils.setField(fileStore, "storeFolder", FILE_STORE_ROOT);

        importer = new ImporterImpl(repository, mapper, lookup, generator, DecoderTypRequest.class, fileStore);

        when(mapper.apply(requestCaptor.capture(), entityCaptor.capture())).thenReturn(ENTITY);
        when(mapper.convert(requestCaptor.capture())).thenReturn(ENTITY);
        when(lookup.find(entityCaptor.capture())).thenReturn(Optional.empty());
        when(repository.saveAndFlush(entityCaptor.capture())).thenReturn(ENTITY);
    }

    @Test
    public void testRead() throws Exception {
        ClassPathResource csv = new ClassPathResource("decoderTyp.csv");
        
        importer.read(new InputStreamReader(csv.getInputStream()));

        // Tests
        DecoderTypRequest request = requestCaptor.getValue();
        assertTrue(reflectionEquals(REQUEST, request));

        DecoderTyp entity = entityCaptor.getValue();
        assertTrue(reflectionEquals(ENTITY, entity));
    }
}
