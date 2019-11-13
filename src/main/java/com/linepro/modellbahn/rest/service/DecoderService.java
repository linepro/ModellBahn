package com.linepro.modellbahn.rest.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderAdress;
import com.linepro.modellbahn.model.IDecoderCV;
import com.linepro.modellbahn.model.IDecoderFunktion;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.model.impl.DecoderAdress;
import com.linepro.modellbahn.model.impl.DecoderCV;
import com.linepro.modellbahn.model.impl.DecoderFunktion;
import com.linepro.modellbahn.model.util.IDecoderCreator;
import com.linepro.modellbahn.persistence.repository.IDecoderAdressRepository;
import com.linepro.modellbahn.persistence.repository.IDecoderCVRepository;
import com.linepro.modellbahn.persistence.repository.IDecoderFunktionRepository;
import com.linepro.modellbahn.persistence.repository.IDecoderRepository;
import com.linepro.modellbahn.persistence.repository.IDecoderTypRepository;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.ApiMessages;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * DecoderService. CRUD service for Decoder
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.DECODER)
@RestController
@RequestMapping(ApiPaths.DECODER)
public class DecoderService extends AbstractItemService<IDecoder, Decoder> {

    private final IDecoderRepository persister;

    private final IDecoderAdressRepository adressPersister;

    private final IDecoderCVRepository cvPersister;

    private final IDecoderFunktionRepository funktionPersister;

    private final IDecoderTypRepository typPersister;

    private final IDecoderCreator creator;

    @Autowired
    public DecoderService(IDecoderRepository persister, IDecoderTypRepository typPersister,
            IDecoderAdressRepository adressPersister, IDecoderCVRepository cvPersister,
            IDecoderFunktionRepository funktionPersister, IDecoderCreator creator) {
        super(persister);

        this.persister = persister;
        this.typPersister = typPersister;
        this.adressPersister = adressPersister;
        this.cvPersister = cvPersister;
        this.funktionPersister = funktionPersister;
        this.creator = creator;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static IDecoder create() {
        return new Decoder();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static IDecoderAdress createAdress() {
        return new DecoderAdress();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static IDecoderCV createCV() {
        return new DecoderCV();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static IDecoderFunktion createFunktion() {
        return new DecoderFunktion();
    }

    @PostMapping(ApiPaths.DECODER_TYP_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Decoder by hersteller and bestell nr", response = IDecoder.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> addDecoder(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
            @PathVariable(ApiNames.BESTELL_NR) String bestellNr) {
        try {
            IDecoderTyp decoderTyp = typPersister.findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            IDecoder decoder = creator.create(decoderTyp);

            return created(decoder);
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GetMapping(ApiPaths.DECODER_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Decoder by decoderId", response = IDecoder.class)
    public ResponseEntity<?> get(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId) {
        return super.get(persister.findByDecoderId(decoderId));
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Decoderen by example", response = IDecoder.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiNames.ID, value = "Decoder id", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DECODER_ID, value = "Decoder code", example = "00001", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DECODER_TYP, value = "Decoder type", example = "[\"ESU\",\"62400\"]", dataType = "[Ljava.lang.String;", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.BEZEICHNUNG, value = "Decoder description", example = "LokSound M4", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PROTOKOLL, value = "Decoder protocoll code", example = "MFX", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.FAHRSTUFE, value = "Decoder speed steps", example = "27", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),})
    public ResponseEntity<?> search(@RequestBody Map<String, String> arguments) {
        return super.search(arguments);
    }

    @PutMapping(ApiPaths.DECODER_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Decoder by decoderId", response = IDecoder.class)
    public ResponseEntity<?> update(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId, IDecoder entity) {
        return super.update(persister.findByDecoderId(decoderId), entity);
    }

    @DeleteMapping(ApiPaths.DECODER_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Decoder by decoderId", response = IDecoder.class)
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId) {
        return super.delete(persister.findByDecoderId(decoderId));
    }

    @GetMapping(ApiPaths.DECODER_ADRESS_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderAdress by decoderId and position", response = DecoderAdress.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> getAdress(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
            @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index) {
        try {
            logGet(String.format(ApiPaths.DECODER_ADRESS_LOG, getEntityClassName(), decoderId, index));

            IDecoder decoder = persister.findByDecoderId(decoderId);

            if (decoder == null) {
                return badRequest(getMessage(ApiMessages.DECODER_DOES_NOT_EXIST, decoderId));
            }

            IDecoderAdress decoderAdress = adressPersister.findByDecoderIdAndIndex(decoderId, index);

            if (decoderAdress != null) {
                return ok(decoderAdress);
            }

            return notFound();
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PutMapping(ApiPaths.DECODER_ADRESS_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a DecoderAdress by decoderId and position", response = DecoderAdress.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> updateAdress(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId, @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index, @RequestParam(ApiNames.ADRESS) Integer adress) {
        logPut(String.format(ApiPaths.DECODER_ADRESS_LOG, getEntityClassName(), decoderId, index) + ": " + adress);

        try {
            Decoder decoder = persister.findByDecoderId(decoderId);

            if (decoder == null) {
                return badRequest(getMessage(ApiMessages.DECODER_DOES_NOT_EXIST, decoderId));
            }

            IDecoderAdress decoderAdress = adressPersister.findByDecoderIdAndIndex(decoderId, index);

            if (decoderAdress == null) {
                return badRequest(getMessage(ApiMessages.DECODER_ADRESS_DOES_NOT_EXIST, decoderId, index));
            }

            decoderAdress.setAdress(adress);

            persister.saveAndFlush(decoder);

            return accepted(decoderAdress);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @GetMapping(ApiPaths.DECODER_CV_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderCV by decoderId and cv", response = DecoderCV.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> getCv(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
            @RequestParam(ApiPaths.CV_PARAM_NAME) Integer cv) {
        try {
            logGet(String.format(ApiPaths.DECODER_CV_LOG, getEntityClassName(), decoderId, cv));

            IDecoder decoder = persister.findByDecoderId(decoderId);

            if (decoder == null) {
                return badRequest(getMessage(ApiMessages.DECODER_DOES_NOT_EXIST, decoderId));
            }

            IDecoderCV decoderCV = cvPersister.findByDecoderIdAndCv(decoderId, cv);

            if (decoderCV != null) {
                return ok(decoderCV);
            }

            return notFound();
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PutMapping(ApiPaths.DECODER_CV_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a DecoderCV by decoderId and cv", response = DecoderCV.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> updateCv(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
            @PathVariable(ApiPaths.CV_PARAM_NAME) Integer cv, @RequestParam(ApiNames.WERT) Integer wert) {
        logPut(String.format(ApiPaths.DECODER_CV_LOG, getEntityClassName(), decoderId, cv) + ": " + wert);

        try {
            Decoder decoder = persister.findByDecoderId(decoderId);

            if (decoder == null) {
                return badRequest(getMessage(ApiMessages.DECODER_DOES_NOT_EXIST, decoderId));
            }

            IDecoderCV decoderCV = cvPersister.findByDecoderIdAndCv(decoderId, cv);

            if (decoderCV == null) {
                return badRequest(getMessage(ApiMessages.DECODER_CV_DOES_NOT_EXIST, decoderId, cv));
            }

            decoderCV.setWert(wert);

            persister.saveAndFlush(decoder);

            return accepted(decoderCV);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @GetMapping(ApiPaths.DECODER_FUNKTION_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderFunktion by decoderId and fn", response = DecoderFunktion.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> getFunktion(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
            @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe,
            @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion) {
        try {
            logGet(String.format(ApiPaths.DECODER_FUNKTION_LOG, getEntityClassName(), decoderId, reihe, funktion));

            IDecoder decoder = persister.findByDecoderId(decoderId);

            if (decoder == null) {
                return badRequest(getMessage(ApiMessages.DECODER_DOES_NOT_EXIST, decoderId));
            }

            IDecoderFunktion decoderFunktion = funktionPersister.findByDecoderIdAndFunktion(decoderId, reihe, funktion);

            if (decoderFunktion != null) {
                return ok(decoderFunktion);
            }

            return notFound();
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PutMapping(ApiPaths.DECODER_FUNKTION_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a DecoderFunktion by decoderId and fn", response = DecoderFunktion.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> updateFunktion(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
            @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe,
            @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion,
            @RequestParam(ApiNames.BEZEICHNUNG) String descirption) {
        logPut(String.format(ApiPaths.DECODER_FUNKTION_LOG, getEntityClassName(), decoderId, reihe, funktion) + ": "
                + descirption);

        try {
            Decoder decoder = persister.findByDecoderId(decoderId);

            if (decoder == null) {
                return badRequest(getMessage(ApiMessages.DECODER_DOES_NOT_EXIST, decoderId));
            }

            IDecoderFunktion decoderFunktion = funktionPersister.findByDecoderIdAndFunktion(decoderId, reihe, funktion);

            if (decoderFunktion == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_FUNKTION_DOES_NOT_EXIST, decoderId, reihe, funktion));
            }

            decoderFunktion.setBezeichnung(descirption);

            persister.saveAndFlush(decoder);

            return accepted(decoderFunktion);
        } catch (Exception e) {
            return getResponse(e);
        }
    }
}
