package com.linepro.modellbahn.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.Hateoas.PagedSchema;
import com.linepro.modellbahn.util.impexp.impl.SuppressExport;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * IUnterKategorie.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.UNTER_KATEGORIE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.KATEGORIE, ApiNames.KATEGORIE_BEZEICHNUNG, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.UNTER_KATEGORIE)
@Schema(name = ApiNames.UNTER_KATEGORIE, description = "Sub category.")
public class UnterKategorieModel extends SpringdocModel<UnterKategorieModel> implements NamedItemModel, Comparable<UnterKategorieModel> {

    private static final long serialVersionUID = -6922373932038545571L;

    @JsonProperty(ApiNames.KATEGORIE)
    @Schema(description = "Category coding", example = "LOKOMOTIV", accessMode = AccessMode.READ_ONLY)
    private String kategorie;

    @SuppressExport
    @JsonProperty(ApiNames.KATEGORIE_BEZEICHNUNG)
    @Schema(description = "Category description", example = "Lokomotiv", accessMode = AccessMode.READ_ONLY)
    private String kategorieBezeichnung;

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Sub category coding", example = "ELEKTRO", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Sub category description", example = "Elektro")
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int compareTo(UnterKategorieModel other) {
        return new CompareToBuilder()
                .append(kategorie, other.kategorie)
                .append(name, other.name)
                .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                        .append(kategorie)
                        .append(name)
                        .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UnterKategorieModel)) {
            return false;
        }

        UnterKategorieModel other = (UnterKategorieModel) obj;

        return new EqualsBuilder()
                        .append(kategorie, other.kategorie)
                        .append(name, other.name)
                        .isEquals();
    }

    @Schema(name = ApiNames.UNTER_KATEGORIE + "Page")
    public static class PagedUnterKategorieModel extends PagedSchema<UnterKategorieModel>{}
}