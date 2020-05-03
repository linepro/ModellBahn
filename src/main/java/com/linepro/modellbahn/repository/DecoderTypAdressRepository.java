package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface DecoderTypAdressRepository extends ItemRepository<DecoderTypAdress> {

    @Query("SELECT a FROM " + DBNames.DECODER_TYP_ADRESS + 
            " a WHERE a." + DBNames.DECODER_TYP + "." + DBNames.HERSTELLER + "." + DBNames.NAME + " = :" + ApiNames.HERSTELLER + 
            " AND a." + DBNames.DECODER_TYP + "." + ApiNames.BESTELL_NR + " = :" + ApiNames.BESTELL_NR +
            " AND a." + DBNames.INDEX + " = :" + ApiNames.INDEX)
    Optional<DecoderTypAdress> findByIndex(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, @Param(ApiNames.INDEX) Integer index);
}
