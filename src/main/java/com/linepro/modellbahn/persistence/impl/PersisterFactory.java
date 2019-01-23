package com.linepro.modellbahn.persistence.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.linepro.modellbahn.guice.ISessionManagerFactory;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.IPersisterFactory;

/**
 * A factory for creating Persister objects.
 */
public class PersisterFactory implements IPersisterFactory {

    private static final String MODEL_IMPLEMENTATION = "com.linepro.modellbahn.model.impl.";

    protected static final String MODEL_INTERFACE = "com.linepro.modellbahn.model.I";

    /** The entity manager. */
    private final ISessionManagerFactory sessionManagerFactory;

    /** The log manager. */
    private final ILoggerFactory logManager;

    private final Logger logger;

    /** The persisters. */
    private final Map<Class<? extends IItem<?>>,IPersister<? extends IItem<?>>> persisters = new HashMap<>();
    
    /**
     * Instantiates a new persister factory.
     *
     * @param sessionManagerFactory the session manager
     * @param logManager the log manager
     */
    @Inject
    public PersisterFactory(final ISessionManagerFactory sessionManagerFactory, final ILoggerFactory logManager) {
        this.sessionManagerFactory = sessionManagerFactory;
        this.logManager = logManager;
        this.logger = logManager.getLogger(getClass().getName());
    }

    @Override
    public synchronized <I extends IItem<?>> IPersister<I> createPersister(Class<I> interfaceClass) {
        @SuppressWarnings("unchecked")
        IPersister<I> persister = (IPersister<I>) persisters.get(interfaceClass);

        if (persister == null) {
            try {
                Class<?> mappedEntity = mappedEntity(interfaceClass);

                persister = new ItemPersister<>(sessionManagerFactory, logManager, mappedEntity);

                registerConverter(persister, interfaceClass);

                persisters.put(interfaceClass, persister);
            } catch (ClassNotFoundException e) {
                logger.error("Cannot map class "  + interfaceClass);
            }
        }

        return persister;
    }
    
    private Class<?> mappedEntity(Class<?> interfaceClass) throws ClassNotFoundException {
        String interfaceName = interfaceClass.getName();

        if (interfaceName.startsWith(MODEL_INTERFACE)) {
            // Really really sloppy way of doing it but easier than reflection...
            String implementationName = interfaceClass.getName().replace(MODEL_INTERFACE, MODEL_IMPLEMENTATION);

            logger.info("Mapped class "  + interfaceName + " to " + implementationName);

            return ClassLoader.getSystemClassLoader().loadClass(implementationName);
        }

        return interfaceClass;
    }

    private void registerConverter(IPersister<?> persister, Class<?> entityClass) {
        if (INamedItem.class.isAssignableFrom(entityClass)) {
            ConvertUtils.register(new NamedItemConverter(persister), entityClass);
        }
    }
}
