package com.linepro.modellbahn.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.linepro.modellbahn.entity.Item;

@NoRepositoryBean
public interface ItemRepository<E extends Item> extends JpaRepository<E, Long>, RepositorySearch<E> {
}
