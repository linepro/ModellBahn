package com.linepro.modellbahn.service.impl;

/**
 * ArtikelService. CRUD service for Artikel
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiMessages;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.AnderungMapper;
import com.linepro.modellbahn.converter.entity.ArtikelMapper;
import com.linepro.modellbahn.converter.request.AnderungRequestMapper;
import com.linepro.modellbahn.converter.request.ArtikelRequestMapper;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.AnderungModel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.persistence.util.AssetIdGenerator;
import com.linepro.modellbahn.repository.AnderungRepository;
import com.linepro.modellbahn.repository.ArtikelRepository;
import com.linepro.modellbahn.repository.DecoderRepository;
import com.linepro.modellbahn.repository.lookup.ArtikelLookup;
import com.linepro.modellbahn.request.AnderungRequest;
import com.linepro.modellbahn.request.ArtikelRequest;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;

@Service(PREFIX + "ArtikelService")
public class ArtikelService extends ItemServiceImpl<ArtikelModel, ArtikelRequest,  Artikel> {

    private final ArtikelRepository repository;

    private final DecoderRepository decoderRepository;

    private final FileService fileService;

    private final AnderungRepository anderungRepository;

    private final AnderungMapper anderungMapper;

    private final AnderungRequestMapper anderungRequestMapper;

    private final AssetIdGenerator assetIdGenerator;

    @Autowired
    public ArtikelService(ArtikelRepository repository, DecoderRepository decoderRepository, ArtikelRequestMapper requestMapper, 
                    ArtikelMapper entityMapper, FileService fileService, AnderungRepository anderungRepository, 
                    AnderungMapper anderungMapper, AnderungRequestMapper anderungRequestMapper, AssetIdGenerator assetIdGenerator,
                    ArtikelLookup lookup) {
        super(repository, requestMapper, entityMapper, lookup);

        this.repository = repository;
        this.decoderRepository = decoderRepository;
        this.fileService = fileService;

        this.anderungRepository = anderungRepository;
        this.anderungMapper = anderungMapper;
        this.anderungRequestMapper = anderungRequestMapper;

        this.assetIdGenerator = assetIdGenerator;
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
        return lookup.find(ArtikelModel.builder().artikelId(artikelId).build())
                     .map(this::removeDecoders)
                     .map(a -> true)
                     .orElse(false);
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
    public Optional<ArtikelModel> addDecoder(String artikelId, String decoderId) {
        return repository.findByArtikelId(artikelId)
                         .map(a -> addDecoder(a, decoderId))
                         .map(repository::save)
                         .map(entityMapper::convert);
    }

    @Transactional
    public Optional<ArtikelModel> deleteDecoder(String artikelId, String decoderId) {
        return repository.findByArtikelId(artikelId)
                         .map(a -> removeDecoder(a, decoderId))
                         .map(repository::save)
                         .map(entityMapper::convert);
    }

    protected Artikel addDecoder(Artikel artikel, String decoderId) {
        return decoderRepository.findByDecoderId(decoderId)
                                .filter(d -> isFree(artikel, d))
                                .map(d -> {
                                    artikel.addDecoder(d);
                                    return artikel;
                                    })
                                .orElseThrow(() -> new ModellBahnException(ApiMessages.DECODER_IN_USE));
    }

    protected boolean isFree(Artikel artikel, Decoder decoder) {
        return decoder.getArtikel() == null || artikel.getArtikelId().equals(decoder.getArtikel().getArtikelId());
    }

    protected Artikel removeDecoder(Artikel artikel, String decoderId) {
        for (Decoder decoder : artikel.getDecoders()) {
            if (decoder.getDecoderId().equals(decoderId)) {
                artikel.removeDecoder(decoder);
                return artikel;
            }
        }

        throw new ModellBahnException(ApiMessages.DECODER_NOT_FOUND);
    }

    protected Artikel removeDecoders(Artikel artikel) {
        List<String> decoderIds = artikel.getDecoders()
                                         .stream()
                                         .map(Decoder::getDecoderId)
                                         .collect(Collectors.toList());
        
        for (String decoderId : decoderIds) {
            removeDecoder(artikel, decoderId);
        }

        return artikel;
    }
}
