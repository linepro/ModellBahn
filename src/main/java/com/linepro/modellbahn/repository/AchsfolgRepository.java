package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "AchsfolgRepository")
public interface AchsfolgRepository extends NamedItemRepository<Achsfolg> {
}
