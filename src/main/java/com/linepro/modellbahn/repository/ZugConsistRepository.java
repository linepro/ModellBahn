package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface ZugConsistRepository extends ItemRepository<ZugConsist> {

    @Query("SELECT c FROM " + DBNames.ZUG_CONSIST + " c WHERE c." + DBNames.ZUG + "." + DBNames.NAME + " = :" + ApiNames.ZUG + " AND c." + DBNames.POSITION + " = :" + ApiNames.POSITION)
    Optional<ZugConsist> findByPosition(@Param(ApiNames.ZUG) String zugStr, @Param(ApiNames.POSITION) Integer position);
}
