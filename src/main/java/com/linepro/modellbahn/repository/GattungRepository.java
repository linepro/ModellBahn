package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "GattungRepository")
public interface GattungRepository extends NamedItemRepository<Gattung> {
}
