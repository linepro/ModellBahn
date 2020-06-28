package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository("DecoderTypAdressRepository")
public interface DecoderTypAdressRepository extends ItemRepository<DecoderTypAdress> {

    //@formatter:off
    @Query(value = "SELECT a " + 
                    "FROM   decoderTypAdress a " + 
                    "WHERE  a.decoderTyp.hersteller.name = :" + ApiNames.HERSTELLER + " " +
                    "AND    a.decoderTyp.bestellNr       = :" + ApiNames.BESTELL_NR + " " +
                    "AND    a.position                   = :" + ApiNames.INDEX,
           nativeQuery = false)
    //@formatter:on
    Optional<DecoderTypAdress> findByIndex(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, @Param(ApiNames.INDEX) Integer index);
}
