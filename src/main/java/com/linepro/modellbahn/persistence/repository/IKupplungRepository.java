package com.linepro.modellbahn.persistence.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Kupplung;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;

@Repository
public interface IKupplungRepository extends INamedItemRepository<Kupplung> {
}
