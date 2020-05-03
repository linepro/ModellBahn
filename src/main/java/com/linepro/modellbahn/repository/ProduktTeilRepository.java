package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface ProduktTeilRepository extends ItemRepository<ProduktTeil> {

    @Query("SELECT t FROM " + DBNames.PRODUKT_TEIL + 
            " t WHERE t." + DBNames.PRODUKT + "." + DBNames.HERSTELLER + "." + DBNames.NAME + " = :" + ApiNames.HERSTELLER + 
            " AND t." + DBNames.PRODUKT + "." + DBNames.BESTELL_NR + " = :" + ApiNames.BESTELL_NR +
            " AND t." + DBNames.TEIL + "." + DBNames.HERSTELLER + "." + DBNames.NAME + " = :" + ApiNames.TEIL_HERSTELLER + 
            " AND t." + DBNames.TEIL + "." + DBNames.BESTELL_NR + " = :" + ApiNames.TEIL_BESTELL_NR)
    Optional<ProduktTeil> findByTeil(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, 
            @Param(ApiNames.TEIL_HERSTELLER) String teilHerstellerStr, @Param(ApiNames.TEIL_BESTELL_NR) String teilBestellNr);
}
