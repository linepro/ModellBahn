package com.linepro.modellbahn.rest.util;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.MediaType;

public interface AcceptableMediaTypes {

    MediaType BMP_TYPE = new MediaType("image", "bmp");

    MediaType GIF_TYPE = new MediaType("image", "gif");

    MediaType JPG_TYPE = new MediaType("image", "jpg");

    MediaType PNG_TYPE = new MediaType("image", "png");

    List<String> IMAGES = Arrays.asList( BMP_TYPE.getType(), GIF_TYPE.getType(), JPG_TYPE.getType(), PNG_TYPE.getType() );

    MediaType PDF_TYPE = new MediaType("application", "pdf");

    List<String> DOCUMENTS = Arrays.asList( PDF_TYPE.getType(), MediaType.TEXT_PLAIN_TYPE.getType() );

    Map<MediaType, String> TYPES_TO_EXTENTSIONS = Stream.of(
            new AbstractMap.SimpleEntry<>(BMP_TYPE, ".bmp"),
            new AbstractMap.SimpleEntry<>(GIF_TYPE, ".gif"),
            new AbstractMap.SimpleEntry<>(JPG_TYPE, ".jpg"),
            new AbstractMap.SimpleEntry<>(PNG_TYPE, ".png"),
            new AbstractMap.SimpleEntry<>(PDF_TYPE, ".pdf"),
            new AbstractMap.SimpleEntry<>(MediaType.TEXT_PLAIN_TYPE, ".txt"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


    Map<String, MediaType> EXTENTSIONS_TO_TYPES = Stream.of(
            new AbstractMap.SimpleEntry<>(".bmp", BMP_TYPE),
            new AbstractMap.SimpleEntry<>(".gif", GIF_TYPE),
            new AbstractMap.SimpleEntry<>(".jpg", JPG_TYPE),
            new AbstractMap.SimpleEntry<>(".png", PNG_TYPE),
            new AbstractMap.SimpleEntry<>(".pdf", PDF_TYPE),
            new AbstractMap.SimpleEntry<>(".txt", MediaType.TEXT_PLAIN_TYPE))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
}
