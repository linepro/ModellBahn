/*
 * 
 */
package com.linepro.modellbahn.util.impexp;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.linepro.modellbahn.entity.base.Item;
import com.linepro.modellbahn.repository.base.ItemRepository;
import com.linepro.modellbahn.util.ReflectionUtils;

/**
 * The Class Exporter.
 * @param <E> the element type
 */
public class ExporterImpl<E extends Item> {

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger(ExporterImpl.class);

    private final ItemRepository<E> persister;
    
    private Class<E> persistentClass;

 
    /**
     * Instantiates a new exporter.
     * @param logManager the log manager
     * @param entityClass the entity class
     */
    @SuppressWarnings("unchecked")
    @Autowired
    public ExporterImpl(ItemRepository<E> persister) {
        this.persister = persister;

        persistentClass = (Class<E>) ReflectionUtils.getParameterizedTypes(this.getClass())[0];
    }
    
    /**
     * Write.
     * @param path the path
     */
    public void write(Path path) {
        try (Writer out = Files.newBufferedWriter(path)) {
            List<String> headers = BeanUtils.describe(persistentClass.newInstance()).keySet().stream().collect(Collectors.toList());

            try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers.toArray(new String[0])))) {

                for (E item : persister.findAll()) {
                    writeObject(printer, headers, item);
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    private void writeObject(CSVPrinter printer, List<String> headers, Item item) {
        try {
            List<String> values = new ArrayList<String>();
            
            for (String column : headers) {
 //               values.add(convert(BeanUtils.getProperty(item, column)));
            }
                
            printer.printRecord(values);
        } catch (Exception e) {
            logger.error("", e);
        }
    }
 }
