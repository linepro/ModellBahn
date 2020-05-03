package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface AntriebRepository extends NamedItemRepository<Antrieb> {
}
