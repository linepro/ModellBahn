package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "BahnverwaltungRepository")
public interface BahnverwaltungRepository extends NamedItemRepository<Bahnverwaltung> {
}
