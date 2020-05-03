package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface ZugRepository extends NamedItemRepository<Zug> {
}
