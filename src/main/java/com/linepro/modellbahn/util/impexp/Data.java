package com.linepro.modellbahn.util.impexp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.util.impexp.impl.DataServiceImpl;
import com.linepro.modellbahn.util.impexp.impl.ExporterFactoryImpl;
import com.linepro.modellbahn.util.impexp.impl.ImporterFactoryImpl;

@Import({
    DataController.class,
    DataServiceImpl.class,
    ExporterFactoryImpl.class,
    ImporterFactoryImpl.class
})
@Configuration
public class Data {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    class IncludeNulls {
    }

    @Bean
    public CsvMapper getCsvMapper() {
        return CsvMapper.builder()
                        .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                        .addMixIn(Item.class, IncludeNulls.class)
                        .build();
    }
}
