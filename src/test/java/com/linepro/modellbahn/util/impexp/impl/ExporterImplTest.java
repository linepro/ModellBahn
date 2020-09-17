package com.linepro.modellbahn.util.impexp.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.io.CharArrayWriter;
import java.math.BigDecimal;
import java.util.Collections;

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

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.util.impexp.Exporter;

@ExtendWith(MockitoExtension.class)
public class ExporterImplTest {

    private static final String BESTELL_NR = "62400";
    private static final String BEZEICHNUNG = "Bezeichnung";
    private static final String DECODER_ID = "00010";
    private static final Boolean DELETED = true;
    private static final String ELECTRONIC_SOLUTIONS_ULM = "Electronic Solutions Ulm";
    private static final String ESU = "ESU";
    private static final Integer FAHRSTUFE = 127;
    private static final String LOK_SOUND = "LokSound";
    private static final String MARKLIN_MFX = "Marklin mfx";
    private static final String MFX = "MFX";
    private static final BigDecimal I_MAX = BigDecimal.ONE;
    private static final Long ID = 1L;
    private static final DecoderStatus STATUS = DecoderStatus.INSTALIERT;
    private static final Konfiguration KONFIGURATION = Konfiguration.CV;
    private static final String NAME = "Name";
    private static final Boolean SOUND = true;
    private static final Stecker STECKER = Stecker.MTC21;

    private static final Decoder ENTITY = Decoder.builder()
                                                 .id(ID)
                                                 .decoderId(DECODER_ID)
                                                 .bezeichnung(BEZEICHNUNG)
                                                 .fahrstufe(FAHRSTUFE)
                                                 .status(STATUS)
                                                 .deleted(DELETED)
                                                 .build();

    private static final DecoderModel MODEL = DecoderModel.builder()
                                                          .decoderId(DECODER_ID.toString())
                                                          .bezeichnung(LOK_SOUND)
                                                          .hersteller(ESU)
                                                          .bestellNr(BESTELL_NR)
                                                          .decoderId(DECODER_ID)
                                                          .fahrstufe(FAHRSTUFE)
                                                          .protokoll(MFX)
                                                          .status(STATUS)
                                                          .deleted(DELETED)
                                                          .build();

    private static final String CSV = "name,bezeichnung,deleted\n" +
                                      NAME + "," + BEZEICHNUNG + "," + DELETED +"\n";

    private final JpaRepository<Decoder,Long> repository;

    private final CharArrayWriter writer;

    private final Mutator<Decoder,DecoderModel> mutator;  

    private Exporter exporter;

    public ExporterImplTest(@Mock JpaRepository<Decoder,Long> repository, @Mock Mutator<Decoder,DecoderModel> mutator) { 
        this.repository = repository;
        this.mutator = mutator;
        this.writer = new CharArrayWriter();;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @BeforeEach
    protected void setUp() throws Exception {
        doAnswer(i -> {
            PageRequest pageRequest = (PageRequest) i.getArgument(0);
            Page data = new PageImpl(Collections.singletonList(ENTITY));

            return pageRequest.getPageNumber() == 0 ? data : Page.empty();
        }).when(repository).findAll(any(Pageable.class));

        when(mutator.convert(any())).thenReturn(MODEL);
        when(mutator.get()).thenReturn(MODEL);

        exporter = new ExporterImpl(repository, mutator, DecoderModel.class);
    }

    @Test
    public void testWrite() throws Exception {
        exporter.write(writer);

        assertEquals(CSV, writer.toString());
    }
}
