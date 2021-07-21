package com.linepro.modellbahn.request;

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

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * IZugConsist.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.CONSIST)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.ZUG, ApiNames.POSITION, ApiNames.ARTIKEL_ID, ApiNames.HERSTELLER, ApiNames.BESTELL_NR,
    ApiNames.BEZEICHNUNG, ApiNames.LANGE, ApiNames.BAHNVERWALTUNG, ApiNames.GATTUNG, ApiNames.BETREIBSNUMMER,
    ApiNames.ABBILDUNG, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.FAHRZEUGEN)
@Schema(name = ApiNames.CONSIST, description = "Rolling stock by poisition in a train.")
public class ZugConsistRequest implements ItemRequest {

    /**
     * 
     */
    private static final long serialVersionUID = -819994633399521641L;

    @JsonProperty(ApiNames.ZUG)
    @Schema(description = "Train code", example = "BAVARIA", accessMode = AccessMode.READ_ONLY)
    private String zug;

    @JsonProperty(ApiNames.POSITION)
    @Schema(description = "Contiguous 1 based position in the train (1 = head)", example = "1", accessMode = AccessMode.READ_ONLY)
    private Integer position;

    @JsonProperty(ApiNames.ARTIKEL_ID)
    @Schema(description = "Artikel id", example = "00001", required = true)
    private String artikelId;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(zug)
            .append(position)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ZugConsistRequest)) {
            return false; 
        }

        ZugConsistRequest other = (ZugConsistRequest) obj;

        return new EqualsBuilder()
                .append(zug, other.zug)
                .append(position, other.position)
                .isEquals();
    }
}