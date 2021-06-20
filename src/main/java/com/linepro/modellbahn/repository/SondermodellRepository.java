package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "SondermodellRepository")
public interface SondermodellRepository extends NamedItemRepository<Sondermodell> {
}
