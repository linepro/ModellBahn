package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import com.github.springtestdbunit.annotation.DatabaseSetup;
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
@TestPropertySource(properties = {
    "hibernate.dialect=org.hibernate.dialect.H2Dialect",
    "spring.application.name=ModellBahn",
    "spring.datasource.driverClassName=org.h2.Driver",
    "spring.datasource.url=jdbc:h2:mem:modellbahn;DB_CLOSE_DELAY=-1",
    "spring.datasource.username=sa",
    "spring.datasource.password=password",
    "spring.flyway.enabled=false",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=create",
    "spring.jpa.open-in-view=false",
    "spring.jpa.properties.hibernate.format_sql=false",
    "spring.jpa.properties.hibernate.show-sql=true",
    "spring.main.banner-mode=off",
    })
@DirtiesContext
public class VorbildRepositoryTest {

    @Autowired
    private VorbildRepository vorbildRepository;

    @Test
    @DatabaseSetup("vorbilder.xml")
    public void testFindByName() throws Exception {
        Optional<Vorbild> found = vorbildRepository.findByName("");
        
        assertTrue(found.isPresent());

        Vorbild vorbild = found.get();
        assertTrue(isAvailable(vorbild.getUnterKategorie()));
        assertTrue(isAvailable(vorbild.getUnterKategorie()));
        assertTrue(isAvailable(vorbild.getUnterKategorie()));
        assertTrue(isAvailable(vorbild.getUnterKategorie()));
    }

    @Test
    @DatabaseSetup("vorbilder.xml")
    public void testFindAllPageable() throws Exception {
        Page<Vorbild> page = vorbildRepository.findAll(Pageable.unpaged());

        List<Vorbild> vorbild = page.getContent();
        assertEquals(3, vorbild.size());
        assertFalse(isAvailable(vorbild.get(0).getUnterKategorie()));
    }

}
