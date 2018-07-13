package com.linepro.modellbahn.persistence;

import javax.inject.Inject;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.linepro.modellbahn.model.impl.Achsfolg;
import com.linepro.modellbahn.model.impl.Antrieb;
import com.linepro.modellbahn.persistence.IItemPersister;
import com.linepro.modellbahn.persistence.IItemPersisterFactory;

public class DBPopulator {

    private final IItemPersisterFactory persisterFactory;

    private final Logger logger;

    @Inject
    public DBPopulator(IItemPersisterFactory persisterFactory, ILoggerFactory logManger) {
        this.persisterFactory = persisterFactory;
        this.logger = logManger.getLogger(DBPopulator.class.getName());
    }

    public void populate() throws Exception {
        logger.info("Start population");

        populateAntrieb();
        populateAchsfolg();
        
        logger.info("Population complete");
    }

    protected void populateAntrieb() throws Exception {
        IItemPersister<Antrieb> persister = persisterFactory.create(Antrieb.class);

        long id = 1L;

        persister.add(new Antrieb( id++, "Dampf", "Dampf", false));
        persister.add(new Antrieb( id++, "Diesel", "Diesel", false));
        persister.add(new Antrieb( id++, "Elektro", "Elektro", false));
    }

    protected void populateAchsfolg() throws Exception {
        IItemPersister<Achsfolg> persister = persisterFactory.create(Achsfolg.class);
    
        long id = 1L;

        persister.add(new Achsfolg( id++, "(1'C)D 3'2'T35", "(1'C)D 3'2'T35", false));
        persister.add(new Achsfolg( id++, "1'C 1' h2t", "1'C 1' h2t", false));
        persister.add(new Achsfolg( id++, "1'C h2 3T16", "1'C h2 3T16", false));
        persister.add(new Achsfolg( id++, "1'D 1' h2t", "1'D 1' h2t", false));
        persister.add(new Achsfolg( id++, "1'E 1' h3 2'3'T28", "1'E 1' h3 2'3'T28", false));
        persister.add(new Achsfolg( id++, "1'E h2 2'2T26 KAB5", "1'E h2 2'2T26 KAB5", false));
        persister.add(new Achsfolg( id++, "1'E1' h2t", "1'E1' h2t", false));
        persister.add(new Achsfolg( id++, "2'C 1' h3 2'2'T26", "2'C 1' h3 2'2'T26", false));
        persister.add(new Achsfolg( id++, "2'C1' 2'2'T26", "2'C1' 2'2'T26", false));
        persister.add(new Achsfolg( id++, "2'C1' h3 2'2'T40", "2'C1' h3 2'2'T40", false));
        persister.add(new Achsfolg( id++, "A1 dm", "A1 dm", false));
        persister.add(new Achsfolg( id++, "AA dm", "AA dm", false));
        persister.add(new Achsfolg( id++, "B drn", "B drn", false));
        persister.add(new Achsfolg( id++, "B h2t", "B h2t", false));
        persister.add(new Achsfolg( id++, "B'2' dh 2'2' 2'2' 2'B' dh", "B'2' dh 2'2' 2'2' 2'B' dh", false));
        persister.add(new Achsfolg( id++, "B'B' de", "B'B' de", false));
        persister.add(new Achsfolg( id++, "B'B' dh", "B'B' dh", false));
        persister.add(new Achsfolg( id++, "B'B'", "B'B'", false));
        persister.add(new Achsfolg( id++, "Bo'2' e", "Bo'2' e", false));
        persister.add(new Achsfolg( id++, "Bo'Bo'+2'2'+Bo'Bo'", "Bo'Bo'+2'2'+Bo'Bo'", false));
        persister.add(new Achsfolg( id++, "Bo'Bo'+Bo'Bo'+Bo'Bo'+Bo'Bo'", "Bo'Bo'+Bo'Bo'+Bo'Bo'+Bo'Bo'", false));
        persister.add(new Achsfolg( id++, "C dh", "C dh", false));
        persister.add(new Achsfolg( id++, "C h2t", "C h2t", false));
        persister.add(new Achsfolg( id++, "C'C' dh", "C'C' dh", false));
        persister.add(new Achsfolg( id++, "Co'Co' w6gf", "Co'Co' w6gf", false));
        persister.add(new Achsfolg( id++, "Co'Co'", "Co'Co'", false));
        persister.add(new Achsfolg( id++, "D h2t", "D h2t", false));
        persister.add(new Achsfolg( id++, "D'D h4vt", "D'D h4vt", false));
    }
}