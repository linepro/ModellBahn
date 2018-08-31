package com.linepro.modellbahn.rest.util;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Link;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.LinkSerializer;

public class ListWithLinks<E extends IItem<?>> {

    protected final List<E> entities;
    
    protected final List<Link> links;

    public ListWithLinks(List<E> entities, List<Link> links) {
        this.entities = entities;
        this.links = links;

        Collections.sort(entities, null);
    }

    @JsonGetter
    @JsonView(Views.DropDown.class)
    public List<E> getEntities() {
        return entities;
    }

    @JsonGetter(ApiNames.LINKS)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(contentUsing=LinkSerializer.class)
    public List<Link> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("entities", entities)
                .append("links", links)
                .toString();
    }
    
}
