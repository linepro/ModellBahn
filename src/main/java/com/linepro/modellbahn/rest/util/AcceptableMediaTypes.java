package com.linepro.modellbahn.rest.util;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

public interface AcceptableMediaTypes {

    MediaType BMP_TYPE = new MediaType("image", "bmp");

    MediaType GIF_TYPE = new MediaType("image", "gif");

    MediaType JPG_TYPE = new MediaType("image", "jpg");

    MediaType JPEG_TYPE = new MediaType("image", "jpeg");

    MediaType PDF_TYPE = new MediaType("application", "pdf");

    MediaType PNG_TYPE = new MediaType("image", "png");

    MediaType TEXT_TYPE = new MediaType("text", "plain");

    List<MediaType> DOCUMENT_TYPES = Arrays.asList(PDF_TYPE, TEXT_TYPE);

    List<MediaType> IMAGE_TYPES = Arrays.asList(BMP_TYPE, GIF_TYPE, JPG_TYPE, JPEG_TYPE, PNG_TYPE);
}
