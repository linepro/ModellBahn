package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.repository.TestPersistence.TEST_PROPERTIES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.persistence.Persistence;
import com.linepro.modellbahn.repository.base.Criterion;
import com.linepro.modellbahn.service.criterion.KategorieenCriteria;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class
    })
@ContextConfiguration(classes = {
    Persistence.class,
    TestPersistence.class
    })
@DataJpaTest
@TestPropertySource(locations = TEST_PROPERTIES)
@DirtiesContext
public class KategorieRepositoryTest {

    @Autowired
    private KategorieRepository kategorieRepository;

    @Test
    public void testFindByName() throws Exception {
        Optional<Kategorie> found = kategorieRepository.findByName("LOKOMOTIV");

        assertTrue(found.isPresent());

        Kategorie kategorie = found.get();

        assertEquals("LOKOMOTIV", kategorie.getName());
        assertTrue(isAvailable(kategorie.getUnterKategorien()));
    }

    @Test
    public void testFindAllPageable() throws Exception {
        Page<Kategorie> page = kategorieRepository.findAll(Pageable.unpaged());

        List<Kategorie> kategorien = page.getContent();
        assertEquals(18, kategorien.size());
        assertFalse(isAvailable(kategorien.get(0).getUnterKategorien()));
    }

    @Test
    public void testFindAllCriterion() {
        Criterion criterion = new KategorieenCriteria(Optional.of(Arrays.asList("LOKOMOTIV", "WAGEN")));
        Page<Kategorie> page = kategorieRepository.findAll(criterion, Pageable.unpaged());

        List<Kategorie> kategorien = page.getContent();
        assertEquals(2, kategorien.size());
        assertTrue(isAvailable(kategorien.get(0).getUnterKategorien()));
    }
}
