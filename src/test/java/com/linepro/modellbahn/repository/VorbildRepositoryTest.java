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
import com.linepro.modellbahn.entity.Vorbild;
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
public class VorbildRepositoryTest {

    @Autowired
    private VorbildRepository vorbildRepository;

    @Test
    public void testFindByName() throws Exception {
        Optional<Vorbild> found = vorbildRepository.findByName("BR-89.0");

        assertTrue(found.isPresent());

        Vorbild vorbild = found.get();
        assertTrue(isAvailable(vorbild.getUnterKategorie()));
        assertTrue(isAvailable(vorbild.getBahnverwaltung()));
        assertTrue(isAvailable(vorbild.getGattung()));
        assertTrue(isAvailable(vorbild.getAntrieb()));
        assertTrue(isAvailable(vorbild.getAchsfolg()));
    }

    @Test
    public void testFindAllPageable() throws Exception {
        Page<Vorbild> page = vorbildRepository.findAll(Pageable.unpaged());

        List<Vorbild> vorbild = page.getContent();
        assertEquals(8, vorbild.size());
        assertTrue(isAvailable(vorbild.get(0).getUnterKategorie()));
        assertTrue(isAvailable(vorbild.get(0).getBahnverwaltung()));
        assertTrue(isAvailable(vorbild.get(0).getGattung()));
        assertTrue(isAvailable(vorbild.get(0).getAntrieb()));
        assertTrue(isAvailable(vorbild.get(0).getAchsfolg()));
    }
}
