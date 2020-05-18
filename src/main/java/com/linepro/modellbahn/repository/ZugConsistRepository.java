package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface ZugConsistRepository extends ItemRepository<ZugConsist> {

    //@formatter:off
    @Query(value = "SELECT c " +
                   "FROM   zug_consist c " +
                   "WHERE  c.zug.name = :" + ApiNames.ZUG + " " +
                   "AND    c.position = :" + ApiNames.POSITION,
           nativeQuery = true)
    //@formatter:on
    Optional<ZugConsist> findByPosition(@Param(ApiNames.ZUG) String zugStr, @Param(ApiNames.POSITION) Integer position);
}
