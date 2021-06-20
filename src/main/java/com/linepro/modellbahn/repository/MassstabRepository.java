package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "MassstabRepository")
public interface MassstabRepository extends NamedItemRepository<Massstab> {
}
