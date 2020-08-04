package com.linepro.modellbahn.persistence;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.linepro.modellbahn.repository.base.RepositoryFactoryBean;
import com.linepro.modellbahn.validation.UniqueValidator;

@EnableJpaRepositories(basePackages = "com.linepro.modellbahn.repository", repositoryImplementationPostfix = "Impl", repositoryFactoryBeanClass = RepositoryFactoryBean.class) 
@EntityScan( basePackages = {"com.linepro.modellbahn.entity"} )
@Configuration(PREFIX + "Persistence")
@Import({UniqueValidator.class})
public class Persistence {
}
