package com.linepro.modellbahn.util.impexp;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.util.ReflectionUtils;

public class Importer<E extends AbstractItem<E>> {

    /** The logger. */
    private static final Logger logger = LoggerFactory.getLogger(Importer.class);

    /** The persister. */
    private final IItemRepository<E> persister;
    
    private Class<E> persistentClass;

    @SuppressWarnings("unchecked")
    @Autowired
    public Importer(IItemRepository<E> persister) {
        this.persister = persister;

        persistentClass = (Class<E>) ReflectionUtils.getParameterizedTypes(this.getClass())[0];
    }
    
    public void read(Path path) {
        try ( Reader in = Files.newBufferedReader(path)) {
            CSVParser parser = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in);
            
            for (CSVRecord record : parser.getRecords()) {
                E item = persistentClass.newInstance();

                Map<String, Object> properties = parser.getHeaderMap().keySet().stream()
                        .map(k -> new SimpleEntry<>(k, record.get(k)))
                        .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

                BeanUtils.populate(item, properties);
                
                persister.save(item);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }
}
