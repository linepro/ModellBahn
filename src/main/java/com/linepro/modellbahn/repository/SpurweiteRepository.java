package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "SpurweiteRepository")
public interface SpurweiteRepository extends NamedItemRepository<Spurweite> {
}
