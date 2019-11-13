package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Produkt;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IProduktRepository extends IItemRepository<Produkt> {

    @Query("SELECT p FROM " + DBNames.PRODUKT + " p WHERE p." + DBNames.HERSTELLER + "." + DBNames.NAME + " = :" + ApiNames.HERSTELLER + " AND p." + ApiNames.BESTELL_NR + " = :" + ApiNames.BESTELL_NR)
    Produkt findByHerstellerAndBestellNr(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr);
}
