package com.linepro.modellbahn.util.impexp.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

import java.io.Reader;
import java.io.Writer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.converter.entity.AchsfolgMutator;
import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;
import com.linepro.modellbahn.util.impexp.Exporter;
import com.linepro.modellbahn.util.impexp.Importer;

@ExtendWith(MockitoExtension.class)
public class ImporterImplTest {

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
    
    private final JpaRepository<Achsfolg,Long> repository;

    private final Reader reader;

    private final Mutator<Achsfolg,AchsfolgModel> mutator;  

    private final CsvMapper mapper;
  
    private Importer importer;
    
    public ImporterImplTest(@Mock JpaRepository<Achsfolg,Long> repository, @Mock Reader reader) { 
        this.repository = repository;
        this.reader = reader;
        this.mutator = new AchsfolgMutator();
        this.mapper = CsvMapper.csvBuilder().build();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @BeforeEach
    protected void setUp() throws Exception {
        importer = new ImporterImpl(repository, mutator, mapper);
    }

    @Test
    public void testRead() throws Exception {
        importer.read(reader);
    }
}
