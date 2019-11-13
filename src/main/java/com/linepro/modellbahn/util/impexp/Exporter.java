/*
 * 
 */
package com.linepro.modellbahn.util.impexp;

import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.refs.INamedItemRef;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.util.ReflectionUtils;
import com.linepro.modellbahn.util.Selector;

/**
 * The Class Exporter.
 * @param <E> the element type
 */
public class Exporter<E extends AbstractItem<E>> {

    /** The Constant DATE_FORMAT. */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger(Exporter.class);

    private final IItemRepository<E> persister;
    
    private Class<E> persistentClass;

 
    /**
     * Instantiates a new exporter.
     * @param logManager the log manager
     * @param entityClass the entity class
     */
    @SuppressWarnings("unchecked")
    @Autowired
    public Exporter(IItemRepository<E> persister) {
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

                for (IItem item : persister.findAll()) {
                    writeObject(printer, headers, item);
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    private void writeObject(CSVPrinter printer, List<String> headers, IItem item) {
        try {
            List<String> values = new ArrayList<String>();
            
            for (String column : headers) {
                values.add(convert(BeanUtils.getProperty(item, column)));
            }
                
            printer.printRecord(values);
        } catch (Exception e) {
            logger.error("", e);
        }
    }
    
    /**
     * Convert.
     * @param value the value
     * @return the string
     */
    private String convert(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof IProdukt) {
            return ((IProdukt) value).getBestellNr();
        } else if (value instanceof IDecoderTyp) {
            return ((IDecoderTyp) value).getBestellNr();
        } else if (value instanceof IUnterKategorie) {
            return ((IUnterKategorie) value).getName();
        } else if (value instanceof INamedItemRef) {
            return ((INamedItemRef) value).getName();
        } else if (value instanceof LocalDate) {
            return DATE_FORMAT.format(value);
        } else if (value instanceof BigDecimal) {
            return ((BigDecimal) value).toPlainString();
        }
        
        return value.toString();
    }
}
