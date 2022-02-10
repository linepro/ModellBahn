package com.linepro.modellbahn.util.impexp.impl;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.repository.lookup.Lookup;
import com.linepro.modellbahn.request.DecoderRequest;
import com.linepro.modellbahn.service.DecoderCreator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("DecoderCommitterImpl")
public class DecoderCommitterImpl extends CommitterImpl<DecoderRequest, Decoder, DecoderModel> {

    private final DecoderCreator creator;

    @Override
    @Transactional(
        propagation = REQUIRES_NEW,
        noRollbackFor= {
            ConstraintViolationException.class
            }
        )
    public void saveOrUpdate(JpaRepository<Decoder, Long> repository, Lookup<Decoder, DecoderModel> lookup, Mapper<DecoderRequest, Decoder> mapper,
                             int rowNum, DecoderRequest request, Decoder entity, List<String> errors) {
        Optional<Decoder> found = lookup.find(entity);

        if (found.isEmpty()) {
            creator.create(request);
        } else {
            super.saveOrUpdate(repository, lookup, mapper, rowNum, request, entity, errors);
        }
    }

}
