package com.linepro.modellbahn.util.impexp.impl;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Configuration;

import com.linepro.modellbahn.util.impexp.DataType;
import com.linepro.modellbahn.util.impexp.Exporter;
import com.linepro.modellbahn.util.impexp.ExporterFactory;

@Configuration(PREFIX + "ExporterFactoryImpl")
public class ExporterFactoryImpl implements ExporterFactory, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public Exporter getExporter(DataType type) {
        return (Exporter) beanFactory.getBean(PREFIX + type.getBeanPrefix() + "Exporter");
    }
}
