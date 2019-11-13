package com.linepro.modellbahn.persistence.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Zug;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;

@Repository
public interface IZugRepository extends INamedItemRepository<Zug> {
}
