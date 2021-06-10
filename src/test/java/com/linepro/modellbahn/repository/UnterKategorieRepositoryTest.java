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
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.persistence.Persistence;

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
public class UnterKategorieRepositoryTest {

    @Autowired
    private UnterKategorieRepository unterKategorieRepository;

    @Test
    public void testFindByName() throws Exception {
        Optional<UnterKategorie> found = unterKategorieRepository.findByName("LOKOMOTIV", "DAMPF");

        assertTrue(found.isPresent());

        UnterKategorie unterKategorie = found.get();

        assertEquals("LOKOMOTIV", unterKategorie.getKategorie().getName());
        assertEquals("DAMPF", unterKategorie.getName());
        assertTrue(isAvailable(unterKategorie.getKategorie()));
    }

    @Test
    public void testFindAll() {
        Page<UnterKategorie> page = unterKategorieRepository.findAll(Pageable.unpaged());

        List<UnterKategorie> unterKategorie = page.getContent();
        assertEquals(146, unterKategorie.size());
        assertTrue(isAvailable(unterKategorie.get(145).getKategorie()));
    }
}
