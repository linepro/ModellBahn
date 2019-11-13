package com.linepro.modellbahn.persistence.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linepro.modellbahn.model.util.AbstractItem;

@SuppressWarnings("rawtypes")
public interface IItemRepository<T extends AbstractItem> extends JpaRepository<T, Long> {
}
