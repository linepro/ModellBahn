package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface AnderungRepository extends ItemRepository<Anderung> {

    Optional<Anderung> findByAnderungId(String artikelId, Integer anderungId);
}
