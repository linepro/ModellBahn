package com.linepro.modellbahn.persistence.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Aufbau;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;

@Repository
public interface IAufbauRepository extends INamedItemRepository<Aufbau> {
}
