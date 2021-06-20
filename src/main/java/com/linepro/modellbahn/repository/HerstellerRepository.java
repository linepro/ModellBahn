package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "HerstellerRepository")
public interface HerstellerRepository extends NamedItemRepository<Hersteller> {
}
