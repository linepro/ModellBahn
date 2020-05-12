package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface VorbildRepository extends ItemRepository<Vorbild> {

    //@formatter:off
    @Query("SELECT v " +
           "FROM   vorbild v " + 
           "WHERE  v.gattung.name = :" + ApiNames.GATTUNG)
    //@formatter:on
    Optional<Vorbild> findByGattung(@Param(ApiNames.GATTUNG) String name);
}
