package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface VorbildRepository extends ItemRepository<Vorbild> {

    @Query("SELECT v FROM " + DBNames.VORBILD + " v WHERE v." + DBNames.GATTUNG + "." + DBNames.NAME + " = :" + ApiNames.GATTUNG)
    Optional<Vorbild> findByGattung(@Param(ApiNames.GATTUNG) String name);
}
