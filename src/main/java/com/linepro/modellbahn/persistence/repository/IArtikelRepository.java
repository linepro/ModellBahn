package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IArtikelRepository extends IItemRepository<Artikel> {

    @Query("SELECT a FROM " + DBNames.ARTIKEL + " a WHERE a." + ApiNames.ARTIKEL_ID + " = :" + ApiNames.ARTIKEL_ID)
    Artikel findByArtikelId(@Param(ApiNames.ARTIKEL_ID) String id);
}
