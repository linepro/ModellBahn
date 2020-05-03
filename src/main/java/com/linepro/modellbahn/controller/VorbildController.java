package com.linepro.modellbahn.controller;

import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.AbstractItemController;
import com.linepro.modellbahn.controller.base.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.controller.base.ApiPaths;
import com.linepro.modellbahn.controller.base.FileService;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.GattungModel;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.VorbildService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * VorbildService. CRUD service for Vorbild
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.VORBILD)
@RestController
@RequestMapping(ApiPaths.VORBILD)
public class VorbildController extends AbstractItemController<VorbildModel,Vorbild> {

	private final FileService fileService;

    @Autowired
    public VorbildController(VorbildService service, FileService fileService) {
        super(service);
        
        this.fileService = fileService;
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static VorbildModel create() {
        return new VorbildModel();
    }

    @GetMapping(ApiPaths.VORBILD_PART)

    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Vorbild by gattung", response = VorbildModel.class)
    public ResponseEntity<?> get(@PathVariable(ApiPaths.GATTUNG_PARAM_NAME) String gattung) {
        return super.get(getModel(gattung));
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)

    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Vorbilder by example", response = VorbildModel.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiNames.ID, value = "Vorbild id", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.GATTUNG, value = "Rolling stock class", example = "BR89.0", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.UNTER_KATEGORIE, value = "Category and subcategory", example = "[\"LOKOMOTIV\",\"DAMPF\"]", dataType = "[Ljava.lang.String;", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.BAHNVERWALTUNG, value = "Railway company code", example = "DB", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.HERSTELLER, value = "Manufacturer", example = "Henschel", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.BAUZEIT, value = "Construction date", example = "1934-01-01", dataType = "java.time.LocalDate", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.ANZAHL, value = "Number built", example = "10", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.BETREIBSNUMMER, value = "Service number", example = "89 006", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.ANTRIEB, value = "Drive method", example = "DAMPF", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.ACHSFOLG, value = "Axle configuration", example = "C1HT", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.ANFAHRZUGKRAFT, value = "Tractive Effort in kN", example = "300", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.LEISTUNG, value = "Power in kW", example = "385", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DIENSTGEWICHT, value = "Service weight in metric tons", example = "46", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.GESCHWINDIGKEIT, value = "Maximum speed in km/h", example = "45", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.LANGE, value = "Length over puffers in mm", example = "9600", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.AUSSERDIENST, value = "Out of service date", example = "1962", dataType = "java.time.LocalDate", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DMTREIBRAD, value = "Drive wheel diamerter in mm", example = "1100", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DMLAUFRADVORN, value = "Running wheel diameter front", example = "0", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DMLAUFRADHINTEN, value = "Running wheel diameter rear", example = "0", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.ZYLINDER, value = "Number of cylinders", example = "2", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DMZYLINDER, value = "Cylinder diameter", example = "500", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.KOLBENHUB, value = "Cylinder stroke", example = "550", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.KESSELUBERDRUCK, value = "Boiler pressure in bar", example = "14", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.ROSTFLACHE, value = "Grate area in m²", example = "1.42", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.UBERHITZERFLACHE, value = "Super heater area in m²", example = "11.9", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.WASSERVORRAT, value = "Water capactity in m³", example = "5.5", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.VERDAMPFUNG, value = "Evaporative heater area in m²", example = "118.4", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.FAHRMOTOREN, value = "Number of drive motors", example = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.MOTORBAUART, value = "Engine manufacturer and model (IC engines)", example = "Henschel 12V1516A", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.LEISTUNGSUBERTRAGUNG, value = "Power transfer method (IC engines)", example = "MECHANISH", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.REICHWEITE, value = "Range (fueled vehicles) km", example = "500", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.KAPAZITAT, value = "Battery capacity in kwH", example = "190", dataType = "Number", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.KLASSE, value = "Number of classes (passenger wagens / multiple units)", example = "2", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.SITZPLATZEKL1, value = "First class seating", example = "20", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.SITZPLATZEKL2, value = "Second class seating", example = "80", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.SITZPLATZEKL3, value = "Third class seating", example = "90", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.SITZPLATZEKL4, value = "Fourth class seating", example = "150", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.AUFBAU, value = "Construction", example = "Holz", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.TRIEBKOPF, value = "Number of drive wagons (multiple units)", example = "2", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.MITTELWAGEN, value = "Number of middle wagons (multiple units)", example = "6", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DREHGESTELLBAUART, value = "Bogie Manufacturer and type", example = "Y 25", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
    })
    public ResponseEntity<?> search(@RequestBody Map<String,String> arguments) {
        return super.search(arguments);
    }

    @Override
    @PostMapping(ApiPaths.ADD)

    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Vorbild", response = VorbildModel.class)
    public ResponseEntity<?> add(VorbildModel model) {
        return super.add(model);
    }

    @PutMapping(ApiPaths.VORBILD_PART)

    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Vorbild by name", response = VorbildModel.class)
    public ResponseEntity<?> update(@PathVariable(ApiPaths.GATTUNG_PARAM_NAME) String gattung, VorbildModel model) {
        model.setGattung(GattungModel.builder().name(gattung).build());
        
        return super.update(model);
    }

    @DeleteMapping(ApiPaths.VORBILD_PART)

    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Vorbild by gattung", response = VorbildModel.class)
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.GATTUNG_PARAM_NAME) String gattung) {
        return super.delete(getModel(gattung));
    }

    @PutMapping(ApiPaths.VORBILD_ABBILDUNG_PART)

    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the picture of a named Vorbild", response = VorbildModel.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiPaths.GATTUNG_PARAM_NAME) String gattung, @PathVariable("file") MultipartFile multipart) throws Exception {
        logPut(String.format(ApiPaths.ABBILDUNG_LOG, ApiNames.VORBILD, gattung) + ": " + multipart.getOriginalFilename());

        VorbildModel vorbild = getModel(gattung);

        vorbild.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.VORBILD, ApiNames.ABBILDUNG, gattung));

        return update(vorbild);
    }

    @DeleteMapping(ApiPaths.VORBILD_ABBILDUNG_PART)

    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Removes the picture of a named Vorbild", response = VorbildModel.class)
    @ApiResponses({
        @ApiResponse(code = 304, message = "Not Modified"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiPaths.GATTUNG_PARAM_NAME) String gattung) throws Exception {
        logDelete(String.format(ApiPaths.ABBILDUNG_LOG, ApiNames.VORBILD, gattung));

        Optional<VorbildModel> vorbild = service.get(getModel(gattung));
        
        if (vorbild.isPresent()) {
            VorbildModel vorbildModel = vorbild.get();

            if (vorbildModel.getAbbildung() != null) {
                fileService.deleteFile(vorbildModel.getAbbildung());
                vorbildModel.setAbbildung(null);
                return update(vorbildModel);
            }
            
            return notModified();
        }
        
        return notFound();
    }

    protected VorbildModel getModel(String gattung) {
        return VorbildModel.builder().gattung(GattungModel.builder().name(gattung).build()).build();
    }
}
