package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "AntriebRepository")
public interface AntriebRepository extends NamedItemRepository<Antrieb> {
}
