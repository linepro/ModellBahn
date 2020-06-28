package com.linepro.modellbahn.repository;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository("MotorTypRepository")
public interface MotorTypRepository extends NamedItemRepository<MotorTyp> {
}
