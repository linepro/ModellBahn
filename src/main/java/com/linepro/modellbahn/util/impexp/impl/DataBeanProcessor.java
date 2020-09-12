package com.linepro.modellbahn.util.impexp.impl;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration(PREFIX + "DataBeanProcessor")
public class DataBeanProcessor implements BeanDefinitionRegistryPostProcessor {

    /** TODO: Replace all this configuration with annotations */
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
        try {
            String prefix = PREFIX + name;
            Class<?> modelClass = Class.forName("com.linepro.modellbahn.model." + name + "Model");

            registry.registerBeanDefinition(prefix + "Exporter", BeanDefinitionBuilder.genericBeanDefinition(ExporterImpl.class)
                                                                                      .setLazyInit(true)
                                                                                      .addConstructorArgReference(prefix + "Repository")
                                                                                      .addConstructorArgReference(prefix + "Mutator")
                                                                                      .addConstructorArgValue(modelClass)
                                                                                      .getBeanDefinition());

            registry.registerBeanDefinition(prefix + "Importer", BeanDefinitionBuilder.genericBeanDefinition(ImporterImpl.class)
                                                                                      .setLazyInit(true)
                                                                                      .addConstructorArgReference(prefix + "Repository")
                                                                                      .addConstructorArgReference(prefix + "ModelMutator")
                                                                                      .addConstructorArgValue(modelClass)
                                                                                      .getBeanDefinition());
        } catch (ClassNotFoundException e) {
            log.error("Could not create beans for " + name + ": " + e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
