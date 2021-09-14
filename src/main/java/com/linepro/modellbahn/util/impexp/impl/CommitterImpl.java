package com.linepro.modellbahn.util.impexp.impl;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import org.springframework.transaction.annotation.Transactional;

import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.repository.lookup.Lookup;
import com.linepro.modellbahn.request.ItemRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(PREFIX + "CommitterImpl")
public class CommitterImpl<R extends ItemRequest, E extends Item, M extends ItemModel> {

    @Transactional(
        propagation = REQUIRES_NEW,
        noRollbackFor= {
            ConstraintViolationException.class
            }
        )
    public void saveOrUpdate(JpaRepository<E, Long> repository, Lookup<E,M> lookup, Mapper<R,E> mapper, int rowNum, R request, E entity, List<String> errors) {
        try {
            Optional<E> found = lookup.find(entity);

            E imported = found.map(f -> mapper.apply(request, f))
                              .orElse(entity);
            
            repository.saveAndFlush(imported);
        } catch(Exception e) {
            String error = getError(rowNum, request, e);

            errors.add(error);

            log.error("Error importing {}: {}", error, e);
        }
    }

    protected String getError(int rowNum, R request, Exception e) {
        String error;

        if (e instanceof ConstraintViolationException) {
            error = ((ConstraintViolationException) e).getConstraintViolations()
                                                       .stream()
                                                       .map(c -> c.getMessage())
                                                       .collect(Collectors.joining(","));
        } else if (e.getCause() != null) {
            error = e.getCause().getMessage();
        } else {
            error = e.getMessage();
        }

        return  MessageFormatter.arrayFormat("#{} - {}: {}", new Object[] { rowNum, request, error }).getMessage();
    }
}