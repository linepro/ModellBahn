package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
@JsonComponent
public class PathSerializer extends JsonSerializer<Path> {

    @Value("${filestore.root:./}")
    private String fileRoot;

    @Value("${filestore.name:store}")
    private String storeFolder;

    @Override
    public void serialize(Path value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(
                ServletUriComponentsBuilder.fromCurrentContextPath()
                                           .pathSegment(Paths.get(fileRoot, storeFolder).relativize(value).toString())
                                           .build()
                                           .toString()
                                           );
    }
}