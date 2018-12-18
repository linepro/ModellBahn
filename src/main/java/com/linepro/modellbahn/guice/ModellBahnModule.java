package com.linepro.modellbahn.guice;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.linepro.modellbahn.IModellBahn;
import com.linepro.modellbahn.ModellBahn;
import com.linepro.modellbahn.persistence.IPersisterFactory;
import com.linepro.modellbahn.persistence.ISessionManager;
import com.linepro.modellbahn.persistence.impl.PersisterFactory;
import com.linepro.modellbahn.persistence.impl.SessionManager;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;

/**
 * ModellBahnModule.
 * Guice bindings for ModellBahn
 * @author   $Author$
 * @version  $Id$
 */
public class ModellBahnModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ILoggerFactory.class).toInstance(LoggerFactory.getILoggerFactory());
        bind(EntityManagerFactory.class).toInstance(Persistence.createEntityManagerFactory("ModellBahn"));
        bind(IPersisterFactory.class).to(PersisterFactory.class).in(Scopes.SINGLETON);

        install(new FactoryModuleBuilder().implement(ISessionManager.class, SessionManager.class)
                .build(ISessionManagerFactory.class));

        requestStaticInjection(StaticPersisterFactory.class);

        //bind(IModellBahn.class).to(ModellBahn.class).in(Scopes.SINGLETON);

        install(new FactoryModuleBuilder().implement(IModellBahn.class, ModellBahn.class)
                                          .build(IModellBahnFactory.class));
    }
}
