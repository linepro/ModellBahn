package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface BahnverwaltungRepository extends NamedItemRepository<Bahnverwaltung> {
}
