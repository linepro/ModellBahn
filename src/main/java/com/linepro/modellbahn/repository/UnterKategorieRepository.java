package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "UnterKategorieRepository")
public interface UnterKategorieRepository extends NamedItemRepository<UnterKategorie> {

    //@formatter:off
    @Query(value = "SELECT u " + 
                   "FROM   unterKategorie u " +
                   "WHERE  u.kategorie.name = :" + ApiNames.KATEGORIE + " " +
                   "AND    u.name           = :" + ApiNames.UNTER_KATEGORIE,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "unterKategorie", type = EntityGraphType.FETCH)
    Optional<UnterKategorie> findByName(@Param(ApiNames.KATEGORIE) String kategorieStr, @Param(ApiNames.UNTER_KATEGORIE) String unterKategorieStr);
}
