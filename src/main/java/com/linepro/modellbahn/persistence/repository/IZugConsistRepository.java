package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.ZugConsist;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IZugConsistRepository extends IItemRepository<ZugConsist> {

    @Query("SELECT c FROM " + DBNames.ZUG_CONSIST + " c WHERE c." + DBNames.ZUG + "." + DBNames.NAME + " = :" + ApiNames.ZUG + " AND c." + DBNames.POSITION + " = :" + ApiNames.POSITION)
    ZugConsist findByZugAndPosition(@Param(ApiNames.ZUG) String zugStr, @Param(ApiNames.POSITION) Integer position);
}
