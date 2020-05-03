package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface ArtikelRepository extends ItemRepository<Artikel> {

    @Query("SELECT a FROM " + DBNames.ARTIKEL + " a WHERE a." + ApiNames.ARTIKEL_ID + " = :" + ApiNames.ARTIKEL_ID)
    Optional<Artikel> findByArtikelId(@Param(ApiNames.ARTIKEL_ID) String id);
}
