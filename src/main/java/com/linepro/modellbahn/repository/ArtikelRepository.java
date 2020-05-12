package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface ArtikelRepository extends ItemRepository<Artikel> {

    /*
     * SELECT a
     * FROM Artikel a
     * WHERE a.artikelId = :artikelId
     */
    //@formatter:off
    @Query("SELECT a " + 
           "FROM   artikel a " + 
           "WHERE  a.artikelId = :" + ApiNames.ARTIKEL_ID)
    //@formatter:on
    Optional<Artikel> findByArtikelId(@Param(ApiNames.ARTIKEL_ID) String artikelId);
}
