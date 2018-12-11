package com.linepro.modellbahn.rest.util;

import javax.ws.rs.core.MediaType;

public interface AcceptableMediaTypes {

    public static final MediaType PDF_TYPE = new MediaType("application", "pdf");

    public static final MediaType PNG_TYPE = new MediaType("image", "png");

    public static final MediaType JPG_TYPE = new MediaType("image", "jpg");

    public static final MediaType GIF_TYPE = new MediaType("image", "gif");

    public static final MediaType BMP_TYPE = new MediaType("image", "bmp");
}
