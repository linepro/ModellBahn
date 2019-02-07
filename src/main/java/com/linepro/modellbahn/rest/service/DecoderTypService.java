package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypAdress;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.impl.DecoderTypAdress;
import com.linepro.modellbahn.model.impl.DecoderTypCV;
import com.linepro.modellbahn.model.impl.DecoderTypFunktion;
import com.linepro.modellbahn.model.keys.DecoderTypAdressKey;
import com.linepro.modellbahn.model.keys.DecoderTypKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.DecoderTypDeserializer;
import com.linepro.modellbahn.rest.json.serialization.HerstellerDeserializer;
import com.linepro.modellbahn.rest.json.serialization.ProtokollDeserializer;
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
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.DECODER_TYP)
@Path(ApiPaths.DECODER_TYP)
public class DecoderTypService extends AbstractItemService<DecoderTypKey, IDecoderTyp> {

    private final IPersister<IDecoderTypAdress> adressPersister;

    private final IPersister<IDecoderTypCV> cvPersister;

    private final IPersister<IDecoderTypFunktion> funktionPersister;

    public DecoderTypService() {
        super(IDecoderTyp.class);
        
        adressPersister = StaticPersisterFactory.get().createPersister(IDecoderTypAdress.class);
        cvPersister = StaticPersisterFactory.get().createPersister(IDecoderTypCV.class);
        funktionPersister = StaticPersisterFactory.get().createPersister(IDecoderTypFunktion.class);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static DecoderTyp create() {
        return new DecoderTyp();
    }
    
    @JsonCreator(mode= Mode.PROPERTIES)
    public static DecoderTyp create(@JsonProperty(value = ApiNames.ID) Long id,
            @JsonProperty(value = ApiNames.HERSTELLER)
            @JsonDeserialize(using = HerstellerDeserializer.class) IHersteller hersteller,
            @JsonProperty(value = ApiNames.BESTELL_NR) String bestellNr,
            @JsonProperty(value = ApiNames.PROTOKOLL)
            @JsonDeserialize(using = ProtokollDeserializer.class) IProtokoll protokoll,
            @JsonProperty(value = ApiNames.NAMEN) String name,
            @JsonProperty(value = ApiNames.BEZEICHNUNG) String bezeichnung,
            @JsonProperty(value = ApiNames.GERAUSCH) Boolean sound,
            @JsonProperty(value = ApiNames.KONFIGURATION) String konfigurationStr,
            @JsonProperty(value = ApiNames.STECKER) String steckerStr,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
        Konfiguration konfiguration = Konfiguration.valueOf(konfigurationStr);
        Stecker stecker = Stecker.valueOf(steckerStr);

        return new DecoderTyp(id, hersteller, protokoll, name, bezeichnung, sound, konfiguration, stecker, deleted);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static DecoderTypAdress createAdress() {
        return new DecoderTypAdress();
    }
    
    @JsonCreator(mode= Mode.PROPERTIES)
    public static DecoderTypAdress createAdress(@JsonProperty(value = ApiNames.ID) Long id,
        @JsonProperty(value = ApiNames.DECODER_TYP)
        @JsonDeserialize(using = DecoderTypDeserializer.class) IDecoderTyp decoderTyp,
            @JsonProperty(value = ApiNames.INDEX) Integer index,
            @JsonProperty(value = ApiNames.ADRESS_TYP) String adressTypStr,
            @JsonProperty(value = ApiNames.SPAN) Integer span,
            @JsonProperty(value = ApiNames.WERKSEINSTELLUNG) Integer werkeinstellung,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
        AdressTyp adressTyp = AdressTyp.valueOf(adressTypStr);
        
        return new DecoderTypAdress(id, decoderTyp, index, adressTyp, span, werkeinstellung, deleted);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static DecoderTypCV createCV() {
        return new DecoderTypCV();
    }
    
    @JsonCreator(mode= Mode.PROPERTIES)
    public static DecoderTypCV createCV(@JsonProperty(value = ApiNames.ID) Long id,
        @JsonProperty(value = ApiNames.DECODER_TYP)
        @JsonDeserialize(using = DecoderTypDeserializer.class) IDecoderTyp decoderTyp,
            @JsonProperty(value = ApiNames.NAMEN) String name,
            @JsonProperty(value = ApiNames.BEZEICHNUNG) String bezeichnung,
            @JsonProperty(value = ApiNames.CV) Integer cv,
            @JsonProperty(value = ApiNames.MINIMAL) Integer max,
            @JsonProperty(value = ApiNames.MAXIMAL) Integer min,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
        return new DecoderTypCV(id, decoderTyp, cv, bezeichnung, cv, min, max, deleted);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static DecoderTypFunktion createFunktion() {
        return new DecoderTypFunktion();
    }
    
    @JsonCreator(mode= Mode.PROPERTIES)
    public static DecoderTypFunktion createFunktion(@JsonProperty(value = ApiNames.ID) Long id,
        @JsonProperty(value = ApiNames.DECODER_TYP)
        @JsonDeserialize(using = DecoderTypDeserializer.class) IDecoderTyp decoderTyp,
            @JsonProperty(value = ApiNames.REIHE) Integer reihe,
            @JsonProperty(value = ApiNames.NAMEN) String funktion,
            @JsonProperty(value = ApiNames.BEZEICHNUNG) String bezeichnung,
            @JsonProperty(value = ApiNames.PROGRAMMABLE) Boolean programmable,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
        return new DecoderTypFunktion(id, decoderTyp, reihe, funktion, bezeichnung, programmable,
                deleted);
    }

    @GET
    @Path(ApiPaths.DECODER_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderTyp by hersteller and bestell nr", response = IDecoderTyp.class)
    public Response get(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            return super.get(new DecoderTypKey(findHersteller(herstellerStr, false), bestellNr));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds DecoderTypen by example", response = IDecoderTyp.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Decoder id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DECODER_ID, value = "Decoder code", example = "00001", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DECODER_TYP, value = "Decoder type", example = "[\"ESU\",\"62400\"]", dataType = "[Ljava.lang.String;", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BEZEICHNUNG, value = "Decoder description", example = "LokSound M4", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PROTOKOLL, value = "Decoder protocoll code", example = "MFX", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.FAHRSTUFE, value = "Decoder speed steps", example = "27", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
    })
    public Response search(@Context UriInfo uriInfo) {
        return super.search(uriInfo);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a DecoderTyp", response = IDecoderTyp.class)
    public Response add(DecoderTyp entity) {
        return super.add(entity);
    }

    @PUT
    @Path(ApiPaths.DECODER_TYP_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code=202, value = "Updates a DecoderTyp by hersteller and bestell nr", response = IDecoderTyp.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response update(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, DecoderTyp entity) {
        try {
            return super.update(new DecoderTypKey(findHersteller(herstellerStr, false), bestellNr), entity);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DELETE
    @Path(ApiPaths.DECODER_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderTyp by hersteller and bestell nr", response = IDecoderTyp.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response delete(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            return super.delete(new DecoderTypKey(findHersteller(herstellerStr, false), bestellNr));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @GET
    @Path(ApiPaths.DECODER_TYP_ADRESS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Finds a DecoderTypAdress by hersteller and bestell nr", response = IDecoderTypAdress.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response getAdress(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.INDEX_PARAM_NAME) Integer index) {
        try {
            logGet(String.format(ApiPaths.DECODER_TYP_ADRESS_LOG, getEntityClassName(), herstellerStr, bestellNr, index));

            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, true);

            if (decoderTyp == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            IDecoderTypAdress decoderTypAdress = findDecoderTypAdress(decoderTyp, index, true);

            if (decoderTypAdress != null) {
                return getResponse(ok(), decoderTypAdress, true, true);
            }

            return getResponse(notFound());
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @POST
    @Path(ApiPaths.DECODER_TYP_ADRESS_ROOT)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Adress to a DecoderTyp", response = IDecoderTypAdress.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response addAdress(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, DecoderTypAdress newDecoderTypAdress) {
        try {
            logPost(String.format(ApiPaths.DECODER_TYP_ADRESS_ROOT_LOG, getEntityClassName(), herstellerStr, bestellNr) + ": " + newDecoderTypAdress);

            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, false);

            if (decoderTyp == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            decoderTyp.addAdress(newDecoderTypAdress);

            getPersister().update(decoderTyp);

            return getResponse(created(), newDecoderTypAdress, true, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PUT
    @Path(ApiPaths.DECODER_TYP_ADRESS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Updates a DecoderTypAdress by hersteller and bestell nr", response = IDecoderTypAdress.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response updateAdress(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.INDEX_PARAM_NAME) Integer index, DecoderTypAdress newDecoderTypAdress) {
        try {
            logPut(String.format(ApiPaths.DECODER_TYP_ADRESS_LOG, getEntityClassName(), herstellerStr, bestellNr, index) + ": " + newDecoderTypAdress);

            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, false);

            if (decoderTyp == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            IDecoderTypAdress decoderTypAdress = findDecoderTypAdress(decoderTyp, index, true);

            if (decoderTypAdress == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            } else if (newDecoderTypAdress.getDecoderTyp() == null) {
                newDecoderTypAdress.setDecoderTyp(decoderTyp);
            } else if (!newDecoderTypAdress.getDecoderTyp().equals(decoderTyp)) {
                // Attempt to change decoderTyp not allowed
                return getResponse(badRequest(ApiMessages.DECODER_TYP_ADRESS_DECODER_TYP_FIXED));
            }

            decoderTypAdress = getAdressPersister().merge(new DecoderTypAdressKey(decoderTyp, index), newDecoderTypAdress);

            return getResponse(accepted(), decoderTypAdress, true, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DELETE
    @Path(ApiPaths.DECODER_TYP_ADRESS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Finds a DecoderTypAdress by hersteller and bestell nr", response = IDecoderTypAdress.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response deleteAdress(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.INDEX_PARAM_NAME) Integer index) {
        try {
            logDelete(String.format(ApiPaths.DECODER_TYP_ADRESS_LOG, getEntityClassName(), herstellerStr, bestellNr, index));

            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, true);

            if (decoderTyp == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            IDecoderTypAdress decoderTypAdress = findDecoderTypAdress(herstellerStr, bestellNr, index, true);

            if (decoderTypAdress == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_ADRESS_DOES_NOT_EXIST, herstellerStr, bestellNr, index)));
            }

            decoderTyp.removeAdress(decoderTypAdress);

            getPersister().update(decoderTyp);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @GET
    @Path(ApiPaths.DECODER_TYP_CV_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Finds a DecoderTypCV by hersteller and bestell nr", response = IDecoderTypCV.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response getCV(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.CV_PARAM_NAME) Integer cv) {
        try {
            logGet(String.format(ApiPaths.DECODER_TYP_CV_LOG, getEntityClassName(), herstellerStr, bestellNr, cv));

            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, true);

            if (decoderTyp == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            IDecoderTypCV decoderTypCV = findDecoderTypCV(decoderTyp, cv, true);

            if (decoderTypCV != null) {
                return getResponse(ok(), decoderTypCV, true, true);
            }

            return getResponse(notFound());
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @POST
    @Path(ApiPaths.DECODER_TYP_CV_ROOT)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Finds a DecoderTypCV by hersteller and bestell nr", response = IDecoderTypCV.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response addCV(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, DecoderTypCV newDecoderTypCV) {
        try {
            logPost(String.format(ApiPaths.DECODER_TYP_CV_ROOT_LOG, getEntityClassName(), herstellerStr, bestellNr) + ": " + newDecoderTypCV);

            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, false);

            if (decoderTyp == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            decoderTyp.addCV(newDecoderTypCV);

            getPersister().update(decoderTyp);

            return getResponse(created(), newDecoderTypCV, true, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PUT
    @Path(ApiPaths.DECODER_TYP_CV_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Finds a DecoderTypCV by hersteller and bestell nr", response = IDecoderTypCV.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response updateCV(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.CV_PARAM_NAME) Integer cv, DecoderTypCV newDecoderTypCV) {
        try {
            logPut(String.format(ApiPaths.DECODER_TYP_CV_LOG, getEntityClassName(), herstellerStr, bestellNr, cv) + ": " + newDecoderTypCV);

            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, false);

            if (decoderTyp == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            IDecoderTypCV decoderTypCV = findDecoderTypCV(decoderTyp, cv, true);

            if (decoderTypCV == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            } else if (newDecoderTypCV.getDecoderTyp() == null) {
                newDecoderTypCV.setDecoderTyp(decoderTyp);
            } else if (!newDecoderTypCV.getDecoderTyp().equals(decoderTyp)) {
                // Attempt to change decoderTyp not allowed
                return getResponse(badRequest(ApiMessages.DECODER_TYP_CV_DECODER_TYP_FIXED));
            }

            // Validate  0 < CV < 256 
            // Validate  0 <= Maximal <= 255
            // Validate  0 <= Minimal <= 255
            // Validate  0 <= Werkeinstelling <= 255
            // Validate  Minimal <= Maximal 
            // Validate bezeichnung unique by decoderTyp

            decoderTypCV = getCVPersister().merge(decoderTypCV.getId(), newDecoderTypCV);

            return getResponse(accepted(), decoderTypCV, true, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DELETE
    @Path(ApiPaths.DECODER_TYP_CV_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Finds a DecoderTypCV by hersteller and bestell nr", response = IDecoderTypCV.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response deleteCV(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.CV_PARAM_NAME) Integer cv) {
        try {
            logDelete(String.format(ApiPaths.DECODER_TYP_CV_LOG, getEntityClassName(), herstellerStr, bestellNr, cv));

            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, true);

            if (decoderTyp == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            IDecoderTypCV decoderTypCV = findDecoderTypCV(herstellerStr, bestellNr, cv, true);

            if (decoderTypCV == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_CV_DOES_NOT_EXIST, herstellerStr, bestellNr, cv)));
            }

            decoderTyp.removeCV(decoderTypCV);

            getPersister().update(decoderTyp);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @GET
    @Path(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Finds a DecoderTypFunktion by hersteller and bestell nr", response = IDecoderTypFunktion.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response getFunktion(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.REIHE_PARAM_NAME) Integer reihe, @PathParam(ApiPaths.FUNKTION_PARAM_NAME) String funktion) {
        try {
            logGet(String.format(ApiPaths.DECODER_TYP_FUNKTION_LOG, getEntityClassName(), herstellerStr, bestellNr, reihe, funktion));

            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, true);

            if (decoderTyp == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            IDecoderTypFunktion decoderTypFunktion = findDecoderTypFunktion(decoderTyp, reihe, funktion, true);

            if (decoderTypFunktion != null) {
                return getResponse(ok(), decoderTypFunktion, true, true);
            }

            return getResponse(notFound());
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @POST
    @Path(ApiPaths.DECODER_TYP_FUNKTION_ROOT)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderTypFunktion by hersteller and bestell nr", response = IDecoderTypFunktion.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response addFunktion(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, DecoderTypFunktion newDecoderTypFunktion) {
        try {
            logPost(String.format(ApiPaths.DECODER_TYP_FUNKTION_ROOT_LOG, getEntityClassName(), herstellerStr, bestellNr) + ": " + newDecoderTypFunktion);

            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, false);

            if (decoderTyp == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            decoderTyp.addFunktion(newDecoderTypFunktion);

            getPersister().update(decoderTyp);

            return getResponse(created(), newDecoderTypFunktion, true, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PUT
    @Path(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Finds a DecoderTypFunktion by hersteller and bestell nr", response = IDecoderTypFunktion.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response updateFunktion(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.REIHE_PARAM_NAME) Integer reihe, @PathParam(ApiPaths.FUNKTION_PARAM_NAME) String funktion, DecoderTypFunktion newDecoderTypFunktion) {
        try {
            logPut(String.format(ApiPaths.DECODER_TYP_FUNKTION_LOG, getEntityClassName(), herstellerStr, bestellNr, reihe, funktion) + ": " + newDecoderTypFunktion);

            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, false);

            if (decoderTyp == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            IDecoderTypFunktion decoderTypFunktion = findDecoderTypFunktion(decoderTyp, reihe, funktion, true);

            if (decoderTypFunktion == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_FUNKTION_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            } else if (newDecoderTypFunktion.getDecoderTyp() == null) {
                newDecoderTypFunktion.setDecoderTyp(decoderTyp);
            } else if (!newDecoderTypFunktion.getDecoderTyp().equals(decoderTyp)) {
                // Attempt to change decoderTyp not allowed
                return getResponse(badRequest(ApiMessages.DECODER_TYP_FUNKTION_DECODER_TYP_FIXED));
            }

            decoderTypFunktion = getFunktionPersister().merge(decoderTypFunktion.getId(), newDecoderTypFunktion);

            return getResponse(accepted(), decoderTypFunktion, true, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DELETE
    @Path(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(code = 204, value = "Deletes a DecoderTypFunktion by hersteller and bestell nr", response = IDecoderTypFunktion.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response deleteFunktion(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.REIHE_PARAM_NAME) Integer reihe, @PathParam(ApiPaths.FUNKTION_PARAM_NAME) String funktion) {
        try {
            logDelete(String.format(ApiPaths.DECODER_TYP_FUNKTION_LOG, getEntityClassName(), herstellerStr, bestellNr, reihe, funktion));


            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, true);

            if (decoderTyp == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            IDecoderTypFunktion decoderTypFunktion = findDecoderTypFunktion(herstellerStr, bestellNr, reihe, funktion, true);

            if (decoderTypFunktion == null) {
                return getResponse(badRequest(getMessage(ApiMessages.DECODER_TYP_FUNKTION_DOES_NOT_EXIST, herstellerStr, bestellNr, reihe, funktion)));
            }

            decoderTyp.removeFunktion(decoderTypFunktion);

            getPersister().update(decoderTyp);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    private IPersister<IDecoderTypAdress> getAdressPersister() {
        return adressPersister;
    }

    private IPersister<IDecoderTypCV> getCVPersister() {
        return cvPersister;
    }

    private IPersister<IDecoderTypFunktion> getFunktionPersister() {
        return funktionPersister;
    }

}
