package com.linepro.modellbahn.util.impexp.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.repository.lookup.Lookup;
import com.linepro.modellbahn.request.ItemRequest;

public interface Committer<R extends ItemRequest, E extends Item, M extends ItemModel> {

    void saveOrUpdate(JpaRepository<E, Long> repository, Lookup<E, M> lookup, Mapper<R, E> mapper, int rowNum, R request, E entity,
                    List<String> errors);
}
