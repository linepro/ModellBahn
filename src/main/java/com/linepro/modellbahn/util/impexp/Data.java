package com.linepro.modellbahn.util.impexp;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.linepro.modellbahn.util.impexp.impl.CommitterImpl;
import com.linepro.modellbahn.util.impexp.impl.CsvSchemaGenerator;
import com.linepro.modellbahn.util.impexp.impl.DataBeanProcessor;
import com.linepro.modellbahn.util.impexp.impl.DataServiceImpl;
import com.linepro.modellbahn.util.impexp.impl.ExporterFactoryImpl;
import com.linepro.modellbahn.util.impexp.impl.ImporterFactoryImpl;

@Import({
    CommitterImpl.class,
    CsvSchemaGenerator.class,
    DataController.class,
    DataServiceImpl.class,
    DataBeanProcessor.class,
    ExporterFactoryImpl.class,
    ImporterFactoryImpl.class
})
@Configuration(PREFIX + "Data")
public class Data {
}
