package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository(PREFIX + "ZugConsistRepository")
public interface ZugConsistRepository extends ItemRepository<ZugConsist> {

    //@formatter:off
    @Query(value = "SELECT c " +
                   "FROM   zugConsist c " +
                   "WHERE  c.zug.name = :" + ApiNames.ZUG + " " +
                   "AND    c.position = :" + ApiNames.POSITION,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "zugConsist", type = EntityGraphType.FETCH)
    Optional<ZugConsist> findByPosition(@Param(ApiNames.ZUG) String zugStr, @Param(ApiNames.POSITION) Integer position);

    @EntityGraph(value = "zugConsist", type = EntityGraphType.FETCH)
    Page<ZugConsist> findAll(Pageable pageable);
}
