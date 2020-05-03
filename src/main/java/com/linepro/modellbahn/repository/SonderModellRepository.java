package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.SonderModell;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface SonderModellRepository extends NamedItemRepository<SonderModell> {
}
