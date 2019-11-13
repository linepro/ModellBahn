package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IUnterKategorieRepository extends INamedItemRepository<UnterKategorie> {

    @Query("SELECT u FROM " + DBNames.UNTER_KATEGORIE + " u WHERE u." + DBNames.KATEGORIE + "." + DBNames.NAME + " = :" + ApiNames.KATEGORIE + " AND u." + DBNames.NAME + " = :" + ApiNames.UNTER_KATEGORIE)
    UnterKategorie findByKategorieAndName(@Param(ApiNames.KATEGORIE) String kategorieStr, @Param(ApiNames.UNTER_KATEGORIE) String unterKategorieStr);
}
