package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Wahrung;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface WahrungRepository extends NamedItemRepository<Wahrung> {
}
