package com.linepro.modellbahn.rest.util;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.MediaType;

public interface AcceptableMediaTypes {

    public static final String BMP = "bmp";

    public static final String GIF = "gif";

    public static final String JPG = "jpg";

    public static final String PDF = "pdf";

    public static final String PNG = "png";

    public static final String TXT = "txt";

    public static final  MediaType BMP_TYPE = new MediaType("image", BMP);

    public static final MediaType GIF_TYPE = new MediaType("image", GIF);

    public static final MediaType JPG_TYPE = new MediaType("image", JPG);

    public static final MediaType PDF_TYPE = new MediaType("application", PDF);

    public static final MediaType PNG_TYPE = new MediaType("image", PNG);

    public static final MediaType TEXT_TYPE = MediaType.TEXT_PLAIN_TYPE;

    public static final Map<String, MediaType> DOCUMENTS = Stream.of(
            new AbstractMap.SimpleEntry<>(PDF, PDF_TYPE),
            new AbstractMap.SimpleEntry<>(TXT, TEXT_TYPE))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public static final Map<String, MediaType> IMAGES = Stream.of(
            new AbstractMap.SimpleEntry<>(BMP, BMP_TYPE),
            new AbstractMap.SimpleEntry<>(GIF, GIF_TYPE),
            new AbstractMap.SimpleEntry<>(JPG, JPG_TYPE),
            new AbstractMap.SimpleEntry<>(PNG, PNG_TYPE))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
}
