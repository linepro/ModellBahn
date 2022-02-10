package com.linepro.modellbahn.service;

import java.util.Optional;

import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.request.DecoderRequest;

public interface DecoderCreator {

    Optional<Decoder> create(DecoderRequest request);

}
