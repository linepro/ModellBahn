package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.List;
import java.util.Map;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.hateoas.mediatype.hal.HalConfiguration.RenderSingleLinks;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.extern.slf4j.Slf4j;

@Import({
    AchsfolgModelProcessor.class, 
    AnderungModelProcessor.class, 
    AntriebModelProcessor.class, 
    ArtikelModelProcessor.class, 
    AufbauModelProcessor.class, 
    BahnverwaltungModelProcessor.class, 
    DecoderAdressModelProcessor.class, 
    DecoderCvModelProcessor.class, 
    DecoderFunktionModelProcessor.class, 
    DecoderModelProcessor.class, 
    DecoderTypAdressModelProcessor.class, 
    DecoderTypCvModelProcessor.class, 
    DecoderTypFunktionModelProcessor.class, 
    DecoderTypModelProcessor.class, 
    EpochModelProcessor.class, 
    GattungModelProcessor.class, 
    HerstellerModelProcessor.class, 
    KategorieModelProcessor.class, 
    KupplungModelProcessor.class, 
    LichtModelProcessor.class, 
    MassstabModelProcessor.class, 
    MotorTypModelProcessor.class, 
    ProduktModelProcessor.class, 
    ProduktTeilModelProcessor.class, 
    ProtokollModelProcessor.class, 
    SondermodellModelProcessor.class, 
    SpurweiteModelProcessor.class, 
    SteuerungModelProcessor.class, 
    UnterKategorieModelProcessor.class, 
    VorbildModelProcessor.class, 
    ZugConsistModelProcessor.class, 
    ZugModelProcessor.class, 
    ZugTypModelProcessor.class
})
@Slf4j
@EnableHypermediaSupport(type = {HypermediaType.HAL})
@Configuration(PREFIX + "Hateoas")
public class Hateoas {

    @Schema(name = "links", description = "HATEOAS Links.", accessMode = AccessMode.READ_ONLY)
    public static class LinksSchema {
    }

    @Schema(name = "pageMetadata", description = "Page description.", accessMode = AccessMode.READ_ONLY)
    public static class PageMetadata {

        @JsonProperty("size")
        @Schema(description = "Page size.", example="20", accessMode = AccessMode.READ_ONLY)
        public int size;

        @JsonProperty("totalElements")
        @Schema(description = "Total number of elements.", example="69", accessMode = AccessMode.READ_ONLY)
        public int totalElements;

        @JsonProperty("totalPages")
        @Schema(description = "Total number of pages.", example="4", accessMode = AccessMode.READ_ONLY)
        public int totalPages;

        @JsonProperty("number")
        @Schema(description = "0 based Page number.", example="0", accessMode = AccessMode.READ_ONLY)
        public int number;
    }

    @Schema(name = "data", description = "Results.", accessMode = AccessMode.READ_ONLY)
    public static class Embedded<T> {
        @Schema(name = ApiNames.DATA, description = "Page data.", accessMode = AccessMode.READ_ONLY)
        public List<T> data;
    }

    @Schema(name = "page", description = "Page of results.", accessMode = AccessMode.READ_ONLY)
    public static class PagedSchema<T> {

        @JsonProperty("_embedded")
        @Schema(description = "Page data.", accessMode = AccessMode.READ_ONLY)
        public Embedded<T> _embedded;

        @JsonProperty("_links")
        @Schema(description = "Set of HATEOAS links", accessMode = AccessMode.READ_ONLY)
        public LinksSchema _links;

        @JsonProperty("page")
        @Schema(description = "Page metadata.", accessMode = AccessMode.READ_ONLY)
        public PageMetadata page;
    }

    @Bean(PREFIX + "OpenApiCustomiser")
    public OpenApiCustomiser getOpenApiCustomiser(ObjectMapper objectMapper) throws JsonMappingException, JsonProcessingException {
        log.info("OpenApiCustomiser");

        /**
         * "_links": { // ObjectSchema ? MapSchema
         *   "rel": [ // ArraySchema
         *     { // ObjectSchema
         *       "href": "url" // StringSchema
         *     }
         *   ]
         */
        ObjectSchema href = new ObjectSchema();
        href.addProperties(ApiNames.HREF, new StringSchema().description("Relation URL").example("https://localhost:8086/ModellBahn/api/achsfolg/1CD32T35").readOnly(true));
        
        ArraySchema rels = new ArraySchema();
        rels.setItems(href);
        rels.description("Relations").example("self").readOnly(true);

        JsonNode example = objectMapper.readTree("{\"self\": [ { \"href\": \"https://linepro2.home:8086/ModellBahn/api/achsfolg/1CD32T35\" } ] }");
        ObjectSchema links = new ObjectSchema();
        links.addProperties("rels", rels);
        links.description("HATEOAS Links").example(example).readOnly(true);

        return openApi -> {
            log.info("OpenApiCustomiser customising");
            openApi.getComponents()
                   .getSchemas()
                   .values()
                   .stream()
                   .filter(s -> s.getProperties() != null)
                   .filter(s -> s.getProperties().containsKey("_links"))
                   .forEach(s -> {
                        @SuppressWarnings("unchecked")
                        Map<String, io.swagger.v3.oas.models.media.Schema<?>> properties = s.getProperties();
                        properties.remove("_links");
                        properties.put("_links", links);
                        log.debug(s.toString());
                   });
        };
    }
    
    @Bean(PREFIX + "HalConfiguration")
    public HalConfiguration getHalConfiguration() {
        return new HalConfiguration().withRenderSingleLinks(RenderSingleLinks.AS_ARRAY); 
    }
}
