package com.linepro.modellbahn.util.impexp.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class DataBeanProcessor implements BeanDefinitionRegistryPostProcessor {

    private static final List<String> EXPORTS = Arrays.asList("Achsfolg", "Anderung", "Antrieb", "Artikel", "Aufbau", "Bahnverwaltung",
                    "DecoderAdress", "DecoderCv", "DecoderFunktion", "Decoder", "DecoderTypAdress", "DecoderTypCv", "DecoderTypFunktion",
                    "DecoderTyp", "Epoch", "Gattung", "Hersteller", "Kategorie", "Kupplung", "Licht", "Massstab", "MotorTyp", "Produkt",
                    "ProduktTeil", "Protokoll", "Sondermodell", "Spurweite", "Steuerung", "UnterKategorie", "Vorbild", "ZugConsist", "Zug", "ZugTyp");

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        EXPORTS.forEach(e -> register(registry, e));
    }

    protected void register(BeanDefinitionRegistry registry, String name) {
        registry.registerBeanDefinition(name + "Exporter",
                        BeanDefinitionBuilder.genericBeanDefinition(ExporterImpl.class)
                                             .addConstructorArgReference(name + "Repository")
                                             .addConstructorArgReference(name + "Mutator")
                                             .addConstructorArgReference("CSVMapper")
                                             .getBeanDefinition());

        registry.registerBeanDefinition(name + "Importer",
                        BeanDefinitionBuilder.genericBeanDefinition(ExporterImpl.class)
                                             .addConstructorArgReference(name + "Repository")
                                             .addConstructorArgReference(name + "ModelMutator")
                                             .addConstructorArgReference("CSVMapper")
                                             .getBeanDefinition());
    }
}
