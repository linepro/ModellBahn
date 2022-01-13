package com.linepro.modellbahn.service.impl;

/**
 * ArtikelService. CRUD service for Artikel
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiMessages;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.AnderungMapper;
import com.linepro.modellbahn.converter.entity.ArtikelMapper;
import com.linepro.modellbahn.converter.entity.DecoderMapper;
import com.linepro.modellbahn.converter.request.AnderungRequestMapper;
import com.linepro.modellbahn.converter.request.ArtikelRequestMapper;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.AnderungModel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.persistence.util.AssetIdGenerator;
import com.linepro.modellbahn.repository.AnderungRepository;
import com.linepro.modellbahn.repository.ArtikelRepository;
import com.linepro.modellbahn.repository.DecoderCvRepository;
import com.linepro.modellbahn.repository.DecoderRepository;
import com.linepro.modellbahn.repository.lookup.ArtikelLookup;
import com.linepro.modellbahn.request.AnderungRequest;
import com.linepro.modellbahn.request.ArtikelDecoderRequest;
import com.linepro.modellbahn.request.ArtikelRequest;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;

@Service(PREFIX + "ArtikelService")
public class ArtikelService extends ItemServiceImpl<ArtikelModel, ArtikelRequest,  Artikel> {

    private final ArtikelRepository repository;

    private final DecoderRepository decoderRepository;

    private final DecoderMapper decoderMapper;

    private final DecoderCvRepository decoderCvRepository;
    
    private final FileService fileService;

    private final AnderungRepository anderungRepository;

    private final AnderungMapper anderungMapper;

    private final AnderungRequestMapper anderungRequestMapper;

    private final AssetIdGenerator assetIdGenerator;

    @Autowired
    public ArtikelService(ArtikelRepository repository, ArtikelRequestMapper requestMapper, ArtikelMapper entityMapper,
                    ArtikelLookup lookup, AssetIdGenerator assetIdGenerator, FileService fileService, AnderungRepository anderungRepository, 
                    AnderungMapper anderungMapper, AnderungRequestMapper anderungRequestMapper, DecoderRepository decoderRepository,
                    DecoderMapper decoderMapper, DecoderCvRepository decoderCvRepository) {
        super(repository, requestMapper, entityMapper, lookup);

        this.repository = repository;

        this.assetIdGenerator = assetIdGenerator;
        this.fileService = fileService;

        this.anderungRepository = anderungRepository;
        this.anderungMapper = anderungMapper;
        this.anderungRequestMapper = anderungRequestMapper;

        this.decoderRepository = decoderRepository;
        this.decoderMapper = decoderMapper;

        this.decoderCvRepository = decoderCvRepository;
    }

    public Optional<ArtikelModel> get(String artikelId) {
        return super.get(ArtikelModel.builder().artikelId(artikelId).build());
    }

    @Override
    public ArtikelModel add(ArtikelRequest request) {
        Artikel artikel = requestMapper.convert(request);
        artikel.setArtikelId(assetIdGenerator.getNextAssetId());
        artikel.setDeleted(false);
        artikel = repository.saveAndFlush(artikel);

        return entityMapper.convert(artikel);
    }

    public Optional<ArtikelModel> update(String artikelId, ArtikelRequest artikelRequest) {
        Optional<Artikel> optArtikel = repository.findByArtikelId(artikelId);

        if (optArtikel.isPresent()) {
           Artikel current = optArtikel.get();
           Boolean deleted = current.getDeleted();

           Artikel updated = requestMapper.apply(artikelRequest, current);

           updated.setDeleted(deleted);
           updated = repository.saveAndFlush(updated);
        }

        return get(artikelId);
    }

    public boolean delete(String artikelId) {
        return super.delete(ArtikelModel.builder().artikelId(artikelId).build());
    }

    @Transactional
    public Optional<ArtikelModel> updateAbbildung(String artikelId, MultipartFile multipart) {
        return repository.findByArtikelId(artikelId)
                         .map(a -> {
                              a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.ARTIKEL, ApiNames.ABBILDUNG, artikelId));
                              return entityMapper.convert(a);
                              });
    }

    @Transactional
    public Optional<ArtikelModel> deleteAbbildung(String artikelId) {
        return repository.findByArtikelId(artikelId)
                         .map(a -> {
                              a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                              return entityMapper.convert(a);
                              });
    }

    @Transactional
    public Optional<ArtikelModel> updateGrossansicht(String artikelId, MultipartFile multipart) {
        return repository.findByArtikelId(artikelId)
                         .map(a -> {
                              a.setGrossansicht(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.ARTIKEL, ApiNames.GROSSANSICHT, artikelId));
                              return entityMapper.convert(a);
                              });
    }

    @Transactional
    public Optional<ArtikelModel> deleteGrossansicht(String artikelId) {
        return repository.findByArtikelId(artikelId)
                         .map(a -> {
                              a.setGrossansicht(fileService.deleteFile(a.getGrossansicht()));
                              return entityMapper.convert(a);
                              });
    }

    @Transactional
    public Optional<AnderungModel> addAnderung(String artikelId, AnderungRequest anderungRequest) {
        return repository.findByArtikelId(artikelId)
                         .map(a -> {
                              Anderung anderung = anderungRequestMapper.convert(anderungRequest);
                              a.addAnderung(anderung);
                              repository.saveAndFlush(a);
                              return anderungMapper.convert(anderung);
                              });
    }

    @Transactional
    public Optional<AnderungModel> updateAnderung(String artikelId, Integer anderungId, AnderungRequest anderungRequest) {
        return anderungRepository.findByAnderungId(artikelId, anderungId)
                                 .map(a -> {
                                      Boolean deleted = a.getDeleted();
                                      Anderung anderung = anderungRequestMapper.apply(anderungRequest, a);
                                      anderung.setDeleted(deleted);
                                      anderung.setDeleted(false);
                                      return anderungMapper.convert(anderungRepository.saveAndFlush(anderung));
                                 });
    }

    @Transactional
    public boolean deleteAnderung(String artikelId, Integer anderungId) {
        return anderungRepository.findByAnderungId(artikelId, anderungId)
                                 .map(a -> {
                                      anderungRepository.delete(a);
                                      return true;
                                 })
                                 .orElse(false);
    }

    @Transactional
    public Optional<DecoderModel> addDecoder(String artikelId, ArtikelDecoderRequest request) {
        return decoderRepository.findByDecoderId(request.getDecoderId())
                                .filter(d -> {
                                    if (d.getArtikel() != null && !artikelId.equals(d.getArtikel().getArtikelId())) {
                                        ModellBahnException exception = new ModellBahnException(ApiMessages.DECODER_IN_USE);
                                        exception.setStatus(HttpStatus.BAD_REQUEST);
                                        throw exception;
                                    }
                                    return true;
                                })
                                .map(d -> repository.findByArtikelId(artikelId)
                                                    .map(a -> {
                                                        if (request.getAdress() != null) {
                                                            d.setAdress(request.getAdress());

                                                            decoderCvRepository.findByBezeichnung(d.getDecoderId(), DecoderCvRepository.ADRESSE)
                                                                               .map(c -> {
                                                                                   c.setWert(request.getAdress());
                                                                                   return decoderCvRepository.save(c);
                                                                                   });
;
                                                        }
                                                        d.setAnmerkung(request.getAnmerkung());
                                                        d.setArtikel(a);
                                                        return d;
                                                    })
                                                    .map(decoderRepository::save)
                                                    .map(decoderMapper::convert)
                                )
                                .orElse(Optional.empty());
    }

    @Transactional
    public boolean deleteDecoder(String artikelId, String decoderId) {
        return decoderRepository.findByDecoderId(decoderId)
                                .filter(d -> {
                                    if (d.getArtikel() == null || !artikelId.equals(d.getArtikel().getArtikelId())) {
                                        ModellBahnException exception = new ModellBahnException(ApiMessages.DECODER_IN_USE);
                                        exception.setStatus(HttpStatus.BAD_REQUEST);
                                        throw exception;
                                    }
                                    return true;
                                })
                                .map(d -> {
                                    d.setArtikel(null);
                                    return d;
                                })
                                .map(decoderRepository::save)
                                .map(d -> true)
                                .orElse(false);
    }
}
