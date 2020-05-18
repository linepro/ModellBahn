package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface UnterKategorieRepository extends NamedItemRepository<UnterKategorie> {

    //@formatter:off
    @Query(value = "SELECT u " + 
                   "FROM   unter_kategorie u " +
                   "WHERE  u.kategorie.name = :" + ApiNames.KATEGORIE + " " +
                   "AND    u.name           = :" + ApiNames.UNTER_KATEGORIE,
           nativeQuery = true)
    //@formatter:on
    Optional<UnterKategorie> findByName(@Param(ApiNames.KATEGORIE) String kategorieStr, @Param(ApiNames.UNTER_KATEGORIE) String unterKategorieStr);
}
