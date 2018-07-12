package com.linepro.modellbahn;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.linepro.modellbahn.guice.ModellBahnModule;
import com.linepro.modellbahn.model.impl.Achsfolg;
import com.linepro.modellbahn.persistence.IItemPersister;
import com.linepro.modellbahn.persistence.IItemPersisterFactory;

public class ModellBahn {

    @SuppressWarnings("unused")
    private final IItemPersisterFactory persisterFactory;

    private final IItemPersister<Achsfolg> achsfolgPersister;

    private final Logger logger;

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ModellBahnModule());
        
        ModellBahn example = injector.getInstance(ModellBahn.class);
        
        example.run();
    }
    
    @Inject
    public ModellBahn(IItemPersisterFactory persisterFactory, ILoggerFactory logManger) {
        this.persisterFactory = persisterFactory;
        this.logger = logManger.getLogger(ModellBahn.class.getName());

        achsfolgPersister = persisterFactory.create(Achsfolg.class);
    }
    
    protected void run() {
        logger.info("Start");

        Achsfolg achsfolg1 = save("Sumith");
        Achsfolg achsfolg2 = save("Anoop");

        logger.info("After insertion");

        list();

        achsfolg1 = update(achsfolg1.getId(), "Sumith Honai");
        achsfolg2 = update(achsfolg2.getId(), "Anoop Pavanai");

        logger.info("After modification");

        list();

        delete(achsfolg2.getId());

        logger.info("After deletion");

        list();
        
        delete();
        
        logger.info("After delete all");

        list();
    }
    
     protected Achsfolg save(String achsfolgName) {
        Achsfolg achsfolg = new Achsfolg();

        achsfolg.setName(achsfolgName);

        return achsfolgPersister.save(achsfolg);
    }

    protected void list() {
        Achsfolg template = new Achsfolg();
        
        template.setDeleted(false);
        
        List<Achsfolg> achsfolgen = achsfolgPersister.search(template);

        for (Achsfolg achsfolg : achsfolgen) {
            logger.info(achsfolg.toString());
        }
    }

    protected Achsfolg update(Long achsfolgId, String achsfolgName) {
        Achsfolg achsfolg = new Achsfolg();

        achsfolg.setId(achsfolgId);
        achsfolg.setName(achsfolgName);

        return achsfolgPersister.update(achsfolg);
    }

    protected void delete(Long achsfolgId) {
        achsfolgPersister.delete(achsfolgId);
    }

    protected void delete() {
        Achsfolg template = new Achsfolg();
        
        template.setDeleted(false);
        
        achsfolgPersister.delete(template);
    }

}