package com.linepro.modellbahn.repository.base;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

import com.linepro.modellbahn.entity.NamedItem;

@NoRepositoryBean
public interface NamedItemRepository<T extends NamedItem> extends ItemRepository<T> {

    Optional<T> findByName(String name);
}
