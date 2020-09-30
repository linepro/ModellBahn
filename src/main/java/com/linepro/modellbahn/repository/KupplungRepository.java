package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "KupplungRepository")
public interface KupplungRepository extends NamedItemRepository<Kupplung> {
}
