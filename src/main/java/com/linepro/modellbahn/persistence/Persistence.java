package com.linepro.modellbahn.persistence;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.linepro.modellbahn.persistence.util.AssetIdGenerator;
import com.linepro.modellbahn.repository.base.RepositoryFactoryBean;
import com.linepro.modellbahn.validation.UniqueValidator;

@EnableJpaRepositories(
    basePackages = { "com.linepro.modellbahn.repository", "com.linepro.modellbahn.security.user" },
    repositoryImplementationPostfix = "Impl", repositoryFactoryBeanClass = RepositoryFactoryBean.class)
@EntityScan(basePackages = {
    "com.linepro.modellbahn.entity", "com.linepro.modellbahn.security.user"
})
@Configuration(PREFIX + "Persistence")
@Import({
    AssetIdGenerator.class,
    UniqueValidator.class
})
public class Persistence {
}
