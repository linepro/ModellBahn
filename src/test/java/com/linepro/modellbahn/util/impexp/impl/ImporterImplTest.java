package com.linepro.modellbahn.util.impexp.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

import java.io.CharArrayReader;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.converter.model.DecoderModelMutator;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.util.impexp.Importer;

@ExtendWith(MockitoExtension.class)
public class ImporterImplTest {

    private static final String DECODER_ID = "00010";
    private static final String HERSTELLER = "ESU";
    private static final String BESTELL_NR = "62400";
    private static final String BEZEICHNUNG = "LokSound M4";
    private static final BigDecimal I_MAX = BigDecimal.ONE;
    private static final String PROTOKOLL = "MFX";
    private static final Integer FAHRSTUFE = 127;
    private static final Boolean GERAUSCH = true;
    private static final Konfiguration KONFIGURATION = Konfiguration.CV;
    private static final Stecker STECKER = Stecker.MTC21;
    private static final LocalDate KAUFDATUM = LocalDate.now();
    private static final String WAHRUNG = "EUR";
    private static final BigDecimal PREIS = BigDecimal.TEN;
    private static final String ANMERKUNG = "remarks";
    private static final DecoderStatus STATUS = DecoderStatus.INSTALIERT;
    private static final String ANLEITUNGEN = "instrutions.pdf";
    private static final Boolean DELETED = true;
    private static final String ARTIKEL_ID = "00010";

    private static final Long ID = 1L;

    private static final Decoder ENTITY = Decoder.builder()
                                                 .id(ID)
                                                 .decoderId(DECODER_ID)
                                                 .bezeichnung(BEZEICHNUNG)
                                                 .fahrstufe(FAHRSTUFE)
                                                 .status(STATUS)
                                                 .deleted(DELETED)
                                                 .build();

    private static final DecoderModel MODEL = DecoderModel.builder()
                                                          .decoderId(DECODER_ID)
                                                          .hersteller(HERSTELLER)
                                                          .bestellNr(BESTELL_NR)
                                                          .artikelId(ARTIKEL_ID)
                                                          .bezeichnung(BEZEICHNUNG)
                                                          .iMax(I_MAX)
                                                          .protokoll(PROTOKOLL)
                                                          .fahrstufe(FAHRSTUFE)
                                                          .sound(GERAUSCH)
                                                          .konfiguration(KONFIGURATION)
                                                          .stecker(STECKER)
                                                          .kaufdatum(KAUFDATUM)
                                                          .wahrung(WAHRUNG)
                                                          .preis(PREIS)
                                                          .anmerkung(ANMERKUNG)
                                                          .status(STATUS)
                                                          .anleitungen(ANLEITUNGEN)
                                                          .deleted(DELETED)
                                                          .build();

    private static final String CSV = "decoderId,hersteller,bestellNr,bezeichnung,iMax,protokoll,fahrstufe,gerausch,konfiguration,stecker,kaufdatum,wahrung,preis,anmerkung,status,anleitungen,deleted\n" +
                                      DECODER_ID + "," + HERSTELLER + "," + BESTELL_NR + ",\"" + BEZEICHNUNG + "\"," + I_MAX + "," + PROTOKOLL + "," + FAHRSTUFE + "," + GERAUSCH + "," + KONFIGURATION + "," + STECKER + "," + KAUFDATUM + "," + WAHRUNG + "," + PREIS + "," + ANMERKUNG + "," + STATUS + "," + ANLEITUNGEN + "," + DELETED +"\n";

    private final JpaRepository<Decoder,Long> repository;

    private final CharArrayReader reader;

    private final Mutator<DecoderModel,Decoder> mutator;  

    private final CsvSchemaGenerator generator;

    private Importer importer;

    public ImporterImplTest(@Mock JpaRepository<Decoder,Long> repository, @Mock DecoderModelMutator mutator) { 
        this.repository = repository;
        this.reader = new CharArrayReader(CSV.toCharArray());
        this.generator = new CsvSchemaGenerator();
        this.mutator = mutator;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @BeforeEach
    protected void setUp() throws Exception {
        importer = new ImporterImpl(repository, mutator, generator, DecoderModel.class);

        doAnswer(i -> {
            assertEquals(MODEL, i.getArgument(0));
            return ENTITY;
        }).when(mutator).convert(any(DecoderModel.class));

        doAnswer(i -> {
            assertEquals(ENTITY, i.getArgument(0));
            return i.getArgument(0);
        }).when(repository).save(any(Decoder.class));
    }

    @Test
    public void testRead() throws Exception {
        importer.read(reader);
    }
}
