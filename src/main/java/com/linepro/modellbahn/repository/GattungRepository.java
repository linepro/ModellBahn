package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface GattungRepository extends NamedItemRepository<Gattung> {
}
