package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface UnterKategorieRepository extends NamedItemRepository<UnterKategorie> {

    @Query("SELECT u FROM " + DBNames.UNTER_KATEGORIE + " u WHERE u." + DBNames.KATEGORIE + "." + DBNames.NAME + " = :" + ApiNames.KATEGORIE + " AND u." + DBNames.NAME + " = :" + ApiNames.UNTER_KATEGORIE)
    Optional<UnterKategorie> findByName(@Param(ApiNames.KATEGORIE) String kategorieStr, @Param(ApiNames.UNTER_KATEGORIE) String unterKategorieStr);
}
