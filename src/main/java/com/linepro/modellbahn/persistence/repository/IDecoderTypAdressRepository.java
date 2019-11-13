package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.DecoderTypAdress;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IDecoderTypAdressRepository extends IItemRepository<DecoderTypAdress> {

    @Query("SELECT a FROM " + DBNames.DECODER_TYP_ADRESS + 
            " a WHERE a." + DBNames.DECODER_TYP + "." + DBNames.HERSTELLER + "." + DBNames.NAME + " = :" + ApiNames.HERSTELLER + 
            " AND a." + DBNames.DECODER_TYP + "." + ApiNames.BESTELL_NR + " = :" + ApiNames.BESTELL_NR +
            " AND a." + DBNames.INDEX + " = :" + ApiNames.INDEX)
    DecoderTypAdress findByTypAndIndex(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, @Param(ApiNames.INDEX) Integer index);
}
