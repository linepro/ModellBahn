package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "LichtRepository")
public interface LichtRepository extends NamedItemRepository<Licht> {
}
