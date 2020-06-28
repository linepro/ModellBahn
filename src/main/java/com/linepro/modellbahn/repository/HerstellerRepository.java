package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository("HerstellerRepository")
public interface HerstellerRepository extends NamedItemRepository<Hersteller> {
}
