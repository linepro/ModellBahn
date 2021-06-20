package com.linepro.modellbahn.util.impexp.impl;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Configuration;

import com.linepro.modellbahn.util.impexp.DataType;
import com.linepro.modellbahn.util.impexp.Importer;
import com.linepro.modellbahn.util.impexp.ImporterFactory;

@Configuration(PREFIX + "ImporterFactoryImpl")
public class ImporterFactoryImpl implements ImporterFactory, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public Importer getImporter(DataType type) {
        return (Importer) beanFactory.getBean(PREFIX + type.getBeanPrefix() + "Importer");
    }
}
