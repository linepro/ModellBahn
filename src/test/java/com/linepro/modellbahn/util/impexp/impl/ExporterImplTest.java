package com.linepro.modellbahn.util.impexp.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

import java.io.CharArrayWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Optional;

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
import com.linepro.modellbahn.converter.entity.AchsfolgMutator;
import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;
import com.linepro.modellbahn.util.impexp.Exporter;

@ExtendWith(MockitoExtension.class)
public class ExporterImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "Name";
    private static final String BEZEICHNUNG = "Bezeichnung";
    private static final Boolean DELETED = true;
    
    private static final Achsfolg ENTITY = Achsfolg.builder()
                                                   .id(ID)
                                                   .name(NAME)
                                                   .bezeichnung(BEZEICHNUNG)
                                                   .deleted(DELETED)
                                                   .build();
    
    private final JpaRepository<Achsfolg,Long> service;

    private final CharArrayWriter writer;

    private final Mutator<Achsfolg,AchsfolgModel> mutator;  

    private final CsvMapper mapper;
    
    private Exporter exporter;
    
    public ExporterImplTest(@Mock JpaRepository<Achsfolg,Long> service) { 
        this.service = service;
        this.writer = new CharArrayWriter();
        this.mutator = new AchsfolgMutator();
        this.mapper = CsvMapper.csvBuilder().build();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @BeforeEach
    protected void setUp() throws Exception {
        exporter = new ExporterImpl(service, mutator, mapper);

        doAnswer(i -> {
            PageRequest pageRequest = (PageRequest) i.getArgument(0);
            Page data = new PageImpl(Collections.singletonList(ENTITY));
            
            return pageRequest.getPageNumber() == 0 ? data : Page.empty();
        }).when(service).findAll(any(Pageable.class));
    }
    
    @Test
    public void testWrite() throws Exception {
        exporter.write(writer);
        
        System.out.println(writer.toString());
    }
}
