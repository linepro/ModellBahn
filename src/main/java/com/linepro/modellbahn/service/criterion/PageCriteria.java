package com.linepro.modellbahn.service.criterion;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.controller.impl.ApiNames;

import lombok.Data;

@Data
public class PageCriteria {

    protected static final Integer FIRST_PAGE = 0;

    protected static final Integer DEFAULT_PAGE_SIZE = 30;

    @JsonProperty(ApiNames.PAGE_NUMBER)
    private Integer page;
    
    @JsonProperty(ApiNames.PAGE_SIZE)
    private Integer size;

    @JsonIgnore
    public Pageable getPageRequest() {
        Optional<Integer> pageNumber = Optional.ofNullable(page);
        Optional<Integer> pageSize = Optional.ofNullable(size);
        
        if (pageNumber.isEmpty() && pageSize.isEmpty()) {
            return Pageable.unpaged();
        }

        return PageRequest.of(pageNumber.orElse(FIRST_PAGE), pageSize.orElse(DEFAULT_PAGE_SIZE));
    }
}