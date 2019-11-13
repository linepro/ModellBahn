package com.linepro.modellbahn.rest.service;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypAdress;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.impl.DecoderTypAdress;
import com.linepro.modellbahn.model.impl.DecoderTypCV;
import com.linepro.modellbahn.model.impl.DecoderTypFunktion;
import com.linepro.modellbahn.persistence.repository.IDecoderTypAdressRepository;
import com.linepro.modellbahn.persistence.repository.IDecoderTypCVRepository;
import com.linepro.modellbahn.persistence.repository.IDecoderTypFunktionRepository;
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
 * DecoderTypService. CRUD service for DecoderTyp, DecoderTypCV and DecoderTypFunktion
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.DECODER_TYP)
@RestController
@RequestMapping(ApiPaths.DECODER_TYP)
public class DecoderTypService extends AbstractItemService<IDecoderTyp, DecoderTyp> {

    private final IDecoderTypRepository persister;

    private final IDecoderTypAdressRepository adressPersister;

    private final IDecoderTypCVRepository cvPersister;

    private final IDecoderTypFunktionRepository funktionPersister;

    @Autowired
    public DecoderTypService(IDecoderTypRepository persister, IDecoderTypAdressRepository adressPersister,
            IDecoderTypCVRepository cvPersister, IDecoderTypFunktionRepository funktionPersister) {
        super(persister);

        this.persister = persister;
        this.adressPersister = adressPersister;
        this.cvPersister = cvPersister;
        this.funktionPersister = funktionPersister;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static DecoderTyp create() {
        return new DecoderTyp();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static IDecoderTypAdress createAdress() {
        return new DecoderTypAdress();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static IDecoderTypCV createCV() {
        return new DecoderTypCV();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static IDecoderTypFunktion createFunktion() {
        return new DecoderTypFunktion();
    }

    @GetMapping(ApiPaths.DECODER_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderTyp by hersteller and bestell nr", response = IDecoderTyp.class)
    public  ResponseEntity<?> get(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            return super.get(getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds DecoderTypen by example", response = IDecoderTyp.class, responseContainer = "List")
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
    public    ResponseEntity<?> search(@RequestBody Map<String, String> arguments) {
        return super.search(arguments);
    }

    @PostMapping(ApiPaths.ADD)
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a DecoderTyp", response = IDecoderTyp.class)
    public  ResponseEntity<?> add(IDecoderTyp entity) {
        return super.add(entity);
    }

    @PutMapping(ApiPaths.DECODER_TYP_PATH)
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a DecoderTyp by hersteller and bestell nr", response = IDecoderTyp.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> update(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, IDecoderTyp entity) {
        try {
            return super.update(getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr), entity);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DeleteMapping(ApiPaths.DECODER_TYP_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderTyp by hersteller and bestell nr", response = IDecoderTyp.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> delete(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            return super.delete(getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @GetMapping(ApiPaths.DECODER_TYP_ADRESS_PATH)
    @ApiOperation(value = "Finds a DecoderTypAdress by hersteller and bestell nr", response = IDecoderTypAdress.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> getAdress(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index) {
        try {
            logGet(String.format(ApiPaths.DECODER_TYP_ADRESS_LOG, getEntityClassName(), herstellerStr, bestellNr,
                    index));

            IDecoderTyp decoderTyp = getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            IDecoderTypAdress decoderTypAdress = getAdressPersister().findByTypAndIndex(herstellerStr, bestellNr, index);

            if (decoderTypAdress != null) {
                return ok(decoderTypAdress);
            }

            return notFound();
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PostMapping(ApiPaths.DECODER_TYP_ADRESS_ROOT)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Adress to a DecoderTyp", response = IDecoderTypAdress.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> addAdress(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, IDecoderTypAdress newDecoderTypAdress) {
        try {
            logPost(String.format(ApiPaths.DECODER_TYP_ADRESS_ROOT_LOG, getEntityClassName(), herstellerStr, bestellNr)
                    + ": " + newDecoderTypAdress);

            DecoderTyp decoderTyp = getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            decoderTyp.addAdress(newDecoderTypAdress);

            getPersister().saveAndFlush(decoderTyp);

            return created(newDecoderTypAdress);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PutMapping(ApiPaths.DECODER_TYP_ADRESS_PATH)
    @ApiOperation(value = "Updates a DecoderTypAdress by hersteller and bestell nr", response = IDecoderTypAdress.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> updateAdress(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index, IDecoderTypAdress newDecoderTypAdress) {
        logPut(String.format(ApiPaths.DECODER_TYP_ADRESS_LOG, getEntityClassName(), herstellerStr, bestellNr, index)
                + ": " + newDecoderTypAdress);

        try {
            DecoderTyp decoderTyp = getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            DecoderTypAdress decoderTypAdress = getAdressPersister().findByTypAndIndex(herstellerStr, bestellNr, index);

            if (decoderTypAdress == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            } else if (newDecoderTypAdress.getDecoderTyp() == null) {
                newDecoderTypAdress.setDecoderTyp(decoderTyp);
            } else if (!newDecoderTypAdress.getDecoderTyp().equals(decoderTyp)) {
                // Attempt to change decoderTyp not allowed
                return badRequest(ApiMessages.DECODER_TYP_ADRESS_DECODER_TYP_FIXED);
            }

            decoderTypAdress.setAdressTyp(newDecoderTypAdress.getAdressTyp());
            decoderTypAdress.setAdress(newDecoderTypAdress.getAdress());
            decoderTypAdress.setSpan(newDecoderTypAdress.getSpan());
            
            decoderTypAdress = getAdressPersister().saveAndFlush(decoderTypAdress);

            return accepted(decoderTypAdress);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DeleteMapping(ApiPaths.DECODER_TYP_ADRESS_PATH)
    @ApiOperation(value = "Finds a DecoderTypAdress by hersteller and bestell nr", response = IDecoderTypAdress.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> deleteAdress(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index) {
        logDelete(
                String.format(ApiPaths.DECODER_TYP_ADRESS_LOG, getEntityClassName(), herstellerStr, bestellNr, index));

        try {
            DecoderTyp decoderTyp = getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            IDecoderTypAdress decoderTypAdress = getAdressPersister().findByTypAndIndex(herstellerStr, bestellNr, index);

            if (decoderTypAdress == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_ADRESS_DOES_NOT_EXIST, herstellerStr, bestellNr, index));
            }

            decoderTyp.removeAdress(decoderTypAdress);

            getPersister().saveAndFlush(decoderTyp);

            return noContent();
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @GetMapping(ApiPaths.DECODER_TYP_CV_PATH)
    @ApiOperation(value = "Finds a DecoderTypCV by hersteller and bestell nr", response = IDecoderTypCV.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> getCV(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable(ApiPaths.CV_PARAM_NAME) Integer cv) {
        try {
            logGet(String.format(ApiPaths.DECODER_TYP_CV_LOG, getEntityClassName(), herstellerStr, bestellNr, cv));

            IDecoderTyp decoderTyp = getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            IDecoderTypCV decoderTypCV = getCVPersister().findByTypAndCv(herstellerStr, bestellNr, cv);

            if (decoderTypCV != null) {
                return ok(decoderTypCV);
            }

            return notFound();
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PostMapping(ApiPaths.DECODER_TYP_CV_ROOT)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Finds a DecoderTypCV by hersteller and bestell nr", response = IDecoderTypCV.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> addCV(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, IDecoderTypCV newDecoderTypCV) {
        try {
            logPost(String.format(ApiPaths.DECODER_TYP_CV_ROOT_LOG, getEntityClassName(), herstellerStr, bestellNr)
                    + ": " + newDecoderTypCV);

            DecoderTyp decoderTyp = getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            decoderTyp.addCV(newDecoderTypCV);

            getPersister().saveAndFlush(decoderTyp);

            return created(newDecoderTypCV);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PutMapping(ApiPaths.DECODER_TYP_CV_PATH)
    @ApiOperation(value = "Finds a DecoderTypCV by hersteller and bestell nr", response = IDecoderTypCV.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> updateCV(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable(ApiPaths.CV_PARAM_NAME) Integer cv, IDecoderTypCV newDecoderTypCV) {
        logPut(String.format(ApiPaths.DECODER_TYP_CV_LOG, getEntityClassName(), herstellerStr, bestellNr, cv) + ": "
                + newDecoderTypCV);

        try {
            DecoderTyp decoderTyp = getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            DecoderTypCV decoderTypCV = getCVPersister().findByTypAndCv(herstellerStr, bestellNr, cv);

            if (decoderTypCV == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            } else if (newDecoderTypCV.getDecoderTyp() == null) {
                newDecoderTypCV.setDecoderTyp(decoderTyp);
            } else if (!newDecoderTypCV.getDecoderTyp().equals(decoderTyp)) {
                // Attempt to change decoderTyp not allowed
                return badRequest(ApiMessages.DECODER_TYP_CV_DECODER_TYP_FIXED);
            }

            // Validate 0 < CV < 256
            // Validate 0 <= Maximal <= 255
            // Validate 0 <= Minimal <= 255
            // Validate 0 <= Werkeinstelling <= 255
            // Validate Minimal <= Maximal
            // Validate bezeichnung unique by decoderTyp

            decoderTypCV.setBezeichnung(newDecoderTypCV.getBezeichnung());
            decoderTypCV.setMaximal(newDecoderTypCV.getMaximal());
            decoderTypCV.setMinimal(newDecoderTypCV.getMinimal());
            decoderTypCV.setWerkseinstellung(newDecoderTypCV.getWerkseinstellung());
            
            decoderTypCV = getCVPersister().saveAndFlush(decoderTypCV);

            return accepted(decoderTypCV);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DeleteMapping(ApiPaths.DECODER_TYP_CV_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Finds a DecoderTypCV by hersteller and bestell nr", response = IDecoderTypCV.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> deleteCV(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable(ApiPaths.CV_PARAM_NAME) Integer cv) {
        logDelete(String.format(ApiPaths.DECODER_TYP_CV_LOG, getEntityClassName(), herstellerStr, bestellNr, cv));

        try {
            DecoderTyp decoderTyp = getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            IDecoderTypCV decoderTypCV = getCVPersister().findByTypAndCv(herstellerStr, bestellNr, cv);

            if (decoderTypCV == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_CV_DOES_NOT_EXIST, herstellerStr, bestellNr, cv));
            }

            decoderTyp.removeCV(decoderTypCV);

            getPersister().saveAndFlush(decoderTyp);

            return noContent();
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @GetMapping(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Finds a DecoderTypFunktion by hersteller and bestell nr", response = IDecoderTypFunktion.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> getFunktion(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe,
            @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion) {
        try {
            logGet(String.format(ApiPaths.DECODER_TYP_FUNKTION_LOG, getEntityClassName(), herstellerStr, bestellNr,
                    reihe, funktion));

            IDecoderTyp decoderTyp = getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            IDecoderTypFunktion decoderTypFunktion = getFunktionPersister().findByTypAndFunktion(herstellerStr, bestellNr, reihe, funktion);

            if (decoderTypFunktion != null) {
                return ok(decoderTypFunktion);
            }

            return notFound();
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PostMapping(ApiPaths.DECODER_TYP_FUNKTION_ROOT)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderTypFunktion by hersteller and bestell nr", response = IDecoderTypFunktion.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> addFunktion(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, IDecoderTypFunktion newDecoderTypFunktion) {
        try {
            logPost(String.format(ApiPaths.DECODER_TYP_FUNKTION_ROOT_LOG, getEntityClassName(), herstellerStr,
                    bestellNr) + ": " + newDecoderTypFunktion);

            DecoderTyp decoderTyp = getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            decoderTyp.addFunktion(newDecoderTypFunktion);

            getPersister().saveAndFlush(decoderTyp);

            return created(newDecoderTypFunktion);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PutMapping(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @ApiOperation(value = "Finds a DecoderTypFunktion by hersteller and bestell nr", response = IDecoderTypFunktion.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> updateFunktion(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe,
            @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion, IDecoderTypFunktion newDecoderTypFunktion) {
        logPut(String.format(ApiPaths.DECODER_TYP_FUNKTION_LOG, getEntityClassName(), herstellerStr, bestellNr, reihe,
                funktion) + ": " + newDecoderTypFunktion);

        try {
            DecoderTyp decoderTyp = getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            DecoderTypFunktion decoderTypFunktion = getFunktionPersister().findByTypAndFunktion(herstellerStr, bestellNr, reihe, funktion);

            if (decoderTypFunktion == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_FUNKTION_DOES_NOT_EXIST, herstellerStr, bestellNr));
            } else if (newDecoderTypFunktion.getDecoderTyp() == null) {
                newDecoderTypFunktion.setDecoderTyp(decoderTyp);
            } else if (!newDecoderTypFunktion.getDecoderTyp().equals(decoderTyp)) {
                // Attempt to change decoderTyp not allowed
                return badRequest(ApiMessages.DECODER_TYP_FUNKTION_DECODER_TYP_FIXED);
            }

            decoderTypFunktion.setBezeichnung(newDecoderTypFunktion.getBezeichnung());
            decoderTypFunktion.setProgrammable(newDecoderTypFunktion.getProgrammable());
            
            decoderTypFunktion = getFunktionPersister().saveAndFlush(decoderTypFunktion);

            return accepted(decoderTypFunktion);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DeleteMapping(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @ApiOperation(code = 204, value = "Deletes a DecoderTypFunktion by hersteller and bestell nr", response = IDecoderTypFunktion.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public  ResponseEntity<?> deleteFunktion(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe,
            @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion) {
        logDelete(String.format(ApiPaths.DECODER_TYP_FUNKTION_LOG, getEntityClassName(), herstellerStr, bestellNr,
                reihe, funktion));

        try {
            DecoderTyp decoderTyp = getPersister().findByHerstellerAndBestelNr(herstellerStr, bestellNr);

            if (decoderTyp == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            DecoderTypFunktion decoderTypFunktion = getFunktionPersister().findByTypAndFunktion(herstellerStr, bestellNr, reihe, funktion);

            if (decoderTypFunktion == null) {
                return badRequest(getMessage(ApiMessages.DECODER_TYP_FUNKTION_DOES_NOT_EXIST, herstellerStr,
                        bestellNr, reihe, funktion));
            }

            decoderTyp.removeFunktion(decoderTypFunktion);

            getPersister().saveAndFlush(decoderTyp);

            return noContent();
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    private IDecoderTypRepository getPersister() {
        return persister;
    }

    private IDecoderTypAdressRepository getAdressPersister() {
        return adressPersister;
    }

    private IDecoderTypCVRepository getCVPersister() {
        return cvPersister;
    }

    private IDecoderTypFunktionRepository getFunktionPersister() {
        return funktionPersister;
    }

}
