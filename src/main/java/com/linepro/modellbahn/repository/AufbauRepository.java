package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "AufbauRepository")
public interface AufbauRepository extends NamedItemRepository<Aufbau> {
}
