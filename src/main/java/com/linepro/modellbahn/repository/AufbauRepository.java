package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository("AufbauRepository")
public interface AufbauRepository extends NamedItemRepository<Aufbau> {
}
