package com.linepro.modellbahn.rest.util;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

public interface AcceptableMediaTypes {

    public static final  MediaType BMP_TYPE = new MediaType("image", "bmp");

    public static final MediaType GIF_TYPE = new MediaType("image", "gif");

    public static final MediaType JPG_TYPE = new MediaType("image", "jpg");

    public static final MediaType JPEG_TYPE = new MediaType("image", "jpeg");

    public static final MediaType PDF_TYPE = new MediaType("application", "pdf");

    public static final MediaType PNG_TYPE = new MediaType("image", "png");

    public static final MediaType TEXT_TYPE = new MediaType("text", "plain");

    public static final List<MediaType> DOCUMENT_TYPES = Arrays.asList(PDF_TYPE, TEXT_TYPE);

    public static final List<MediaType> IMAGE_TYPES = Arrays.asList(BMP_TYPE, GIF_TYPE, JPG_TYPE, JPEG_TYPE, PNG_TYPE);
}
