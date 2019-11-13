package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Vorbild;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IVorbildRepository extends IItemRepository<Vorbild> {

    @Query("SELECT v FROM " + DBNames.VORBILD + " v WHERE v." + DBNames.GATTUNG + "." + DBNames.NAME + " = :" + ApiNames.GATTUNG)
    Vorbild findByGattung(@Param(ApiNames.GATTUNG) String name);
}
