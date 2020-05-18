package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface DecoderTypAdressRepository extends ItemRepository<DecoderTypAdress> {

    //@formatter:off
    @Query(value = "SELECT a " + 
                    "FROM   decoder_typ_adress a " + 
                    "WHERE  a.decoder_typ.hersteller.name = :" + ApiNames.HERSTELLER + " " +
                    "AND    a.decoder_typ.bestell_nr      = :" + ApiNames.BESTELL_NR + " " +
                    "AND    a.position                    = :" + ApiNames.INDEX,
           nativeQuery = true)
    //@formatter:on
    Optional<DecoderTypAdress> findByIndex(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, @Param(ApiNames.INDEX) Integer index);
}
