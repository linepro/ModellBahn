package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "SteuerungRepository")
public interface SteuerungRepository extends NamedItemRepository<Steuerung> {
}
