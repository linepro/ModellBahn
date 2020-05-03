package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface MassstabRepository extends NamedItemRepository<Massstab> {
}
