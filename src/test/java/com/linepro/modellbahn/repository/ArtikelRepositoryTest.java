package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.repository.TestPersistence.TEST_PROPERTIES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.persistence.Persistence;
import com.linepro.modellbahn.repository.base.Criterion;
import com.linepro.modellbahn.service.criterion.ArtikelCriterion;

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
public class ArtikelRepositoryTest {

    @Autowired
    private ArtikelRepository artikelRepository;

    @Test
    public void testFindByArtikelId() throws Exception {
        Optional<Artikel> found = artikelRepository.findByArtikelId("00001");

        assertTrue(found.isPresent());

        Artikel artikel = found.get();

        assertEquals("00001", artikel.getArtikelId());
        assertTrue(isAvailable(artikel.getProdukt()));
    }

    @Test
    public void testFindAllPageable() throws Exception {
        Page<Artikel> page = artikelRepository.findAll(Pageable.unpaged());

        List<Artikel> artikeln = page.getContent();
        assertEquals(4, artikeln.size());
        assertTrue(isAvailable(artikeln.get(0).getProdukt()));
    }

    @Test
    public void testFindAllCriterion() {
        Criterion criterion = new ArtikelCriterion(Optional.of(new ArtikelModel()));
        Page<Artikel> page = artikelRepository.findAll(criterion, Pageable.unpaged());

        List<Artikel> artikeln = page.getContent();
        assertEquals(4, artikeln.size());
        assertTrue(isAvailable(artikeln.get(0).getProdukt()));
    }
}