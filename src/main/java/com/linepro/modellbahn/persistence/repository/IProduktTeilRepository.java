package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.ProduktTeil;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IProduktTeilRepository extends IItemRepository<ProduktTeil> {

    @Query("SELECT t FROM " + DBNames.PRODUKT_TEIL + 
            " t WHERE t." + DBNames.PRODUKT + "." + DBNames.HERSTELLER + "." + DBNames.NAME + " = :" + ApiNames.HERSTELLER + 
            " AND t." + DBNames.PRODUKT + "." + DBNames.BESTELL_NR + " = :" + ApiNames.BESTELL_NR +
            " AND t." + DBNames.TEIL + "." + DBNames.HERSTELLER + "." + DBNames.NAME + " = :" + ApiNames.TEIL_HERSTELLER + 
            " AND t." + DBNames.TEIL + "." + DBNames.BESTELL_NR + " = :" + ApiNames.TEIL_BESTELL_NR)
    ProduktTeil findByProduktAndTeil(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, 
            @Param(ApiNames.TEIL_HERSTELLER) String teilHerstellerStr, @Param(ApiNames.TEIL_BESTELL_NR) String teilBestellNr);
}
