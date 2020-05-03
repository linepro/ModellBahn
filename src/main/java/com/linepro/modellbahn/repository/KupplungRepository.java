package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface KupplungRepository extends NamedItemRepository<Kupplung> {
}
