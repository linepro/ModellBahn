package com.linepro.modellbahn.util.impexp.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.util.impexp.Exporter;

@ExtendWith(MockitoExtension.class)
public class ExporterImplTest {

    private static final String DECODER_ID = "00010";
    private static final String HERSTELLER = "ESU";
    private static final String BESTELL_NR = "62400";
    private static final String BEZEICHNUNG = "LokSound M4";
    private static final BigDecimal I_MAX = BigDecimal.ONE;
    private static final String PROTOKOLL = "MFX";
    private static final Integer FAHRSTUFE = 127;
    private static final Boolean GERAUSCH = true;
    private static final Boolean MOTOR = true;
    private static final Integer OUTPUTS = 4;
    private static final Integer SERVOS = 0;
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
    private static final AdressTyp ADRESS_TYP = AdressTyp.DIGITAL;
    private static final Integer ADRESS = 3;
    private static final Integer SPAN = 1;

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
                                                          .adressTyp(ADRESS_TYP)
                                                          .adress(ADRESS)
                                                          .span(SPAN)
                                                          .sound(GERAUSCH)
                                                          .motor(MOTOR)
                                                          .outputs(OUTPUTS)
                                                          .servos(SERVOS)
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

    private static final String CSV = "decoderId,hersteller,bestellNr,artikelId,bezeichnung,iMax,protokoll,adressTyp,adress,span,fahrstufe,gerausch,motor,outputs,servos,konfiguration,stecker,kaufdatum,wahrung,preis,anmerkung,status,anleitungen,deleted\n" +
                                      DECODER_ID + "," + HERSTELLER + "," + BESTELL_NR + "," + ARTIKEL_ID + ",\"" + BEZEICHNUNG + "\"," + I_MAX + "," + PROTOKOLL + "," + ADRESS_TYP + "," + ADRESS + "," + SPAN + "," + FAHRSTUFE + "," + GERAUSCH + "," + MOTOR + "," + OUTPUTS + "," + SERVOS + "," + KONFIGURATION + "," + STECKER + "," + KAUFDATUM + "," + WAHRUNG + "," + PREIS + "," + ANMERKUNG + "," + STATUS + "," + ANLEITUNGEN + "," + DELETED +"\n";

    private final JpaRepository<Decoder,Long> repository;

    private CharArrayWriter writer;

    private final Mapper<Decoder,DecoderModel> Mapper;  

    private final CsvSchemaGenerator generator;

    private Exporter exporter;

    @Mock
    private HttpServletResponse response;

    public ExporterImplTest(@Mock JpaRepository<Decoder,Long> repository, @Mock Mapper<Decoder,DecoderModel> Mapper) { 
        this.repository = repository;
        this.Mapper = Mapper;
        this.generator = new CsvSchemaGenerator();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @BeforeEach
    protected void setUp() throws Exception {
        doAnswer(i -> {
            PageRequest pageRequest = (PageRequest) i.getArgument(0);
            Page data = new PageImpl(Collections.singletonList(ENTITY));

            return pageRequest.getPageNumber() == 0 ? data : Page.empty();
        }).when(repository).findAll(any(Pageable.class));

        when(Mapper.convert(any())).thenReturn(MODEL);
        when(Mapper.get()).thenReturn(MODEL);

        writer = new CharArrayWriter();

        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        exporter = new ExporterImpl(repository, Mapper, generator, DecoderModel.class);
    }

    @Test
    public void testWrite() throws Exception {
        exporter.write(response);

        String written = new String(writer.toCharArray());

        assertEquals(CSV, written);
    }
}
