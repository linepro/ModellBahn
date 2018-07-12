package com.linepro.modellbahn.guice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.linepro.modellbahn.ModellBahn;
import com.linepro.modellbahn.persistence.IItemPersisterFactory;
import com.linepro.modellbahn.persistence.ItemPersisterFactory;

public class ModellBahnModule extends AbstractModule {

    @Override
    protected void configure() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("modellbahn");

        //bind(ILoggerFactory.class).to(SubstituteLoggerFactory.class).in(Scopes.SINGLETON);
        bind(ILoggerFactory.class).toInstance(LoggerFactory.getILoggerFactory());
        bind(EntityManager.class).toInstance(entityManagerFactory.createEntityManager());
        bind(IItemPersisterFactory.class).to(ItemPersisterFactory.class).in(Scopes.SINGLETON);
        
        bind(ModellBahn.class).in(Scopes.SINGLETON);
    }
}
