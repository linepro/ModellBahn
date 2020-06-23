package com.linepro.modellbahn.util.impexp.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

import java.io.CharArrayReader;
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

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.converter.model.DecoderModelMutator;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.util.impexp.Importer;

@ExtendWith(MockitoExtension.class)
public class ImporterImplTest {

    private static final Long ID = 1L;
    private static final String DECODER_ID = "00100";
    private static final String BEZEICHNUNG = "Bezeichnung";
    private static final Boolean DELETED = true;
    
    private static final Decoder ENTITY = Decoder.builder()
                                                   .id(ID)
                                                   .decoderId(DECODER_ID)
                                                   .bezeichnung(BEZEICHNUNG)
                                                   .deleted(DELETED)
                                                   .build();

    private static final String CSV = "name,bezeichnung,deleted\n" +
                    DECODER_ID + "," + BEZEICHNUNG + "," + DELETED +"\n";

    private final JpaRepository<Decoder,Long> repository;

    private final CharArrayReader reader;

    private final Mutator<DecoderModel,Decoder> mutator;  

    private final CsvMapper mapper;
  
    private Importer importer;
    
    public ImporterImplTest(@Mock JpaRepository<Decoder,Long> repository, @Mock DecoderModelMutator mutator) { 
        this.repository = repository;
        this.reader = new CharArrayReader(CSV.toCharArray());
        this.mutator = mutator;
        this.mapper = CsvMapper.csvBuilder().build();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @BeforeEach
    protected void setUp() throws Exception {
        importer = new ImporterImpl(repository, mutator, mapper);

        doAnswer(i -> {
            PageRequest pageRequest = (PageRequest) i.getArgument(0);
            Page data = new PageImpl(Collections.singletonList(ENTITY));
            
            return pageRequest.getPageNumber() == 0 ? data : Page.empty();
        }).when(repository).findAll(any(Pageable.class));
    }

    @Test
    public void testRead() throws Exception {
        importer.read(reader);

        assertEquals(CSV, reader.toString());
    }
}
