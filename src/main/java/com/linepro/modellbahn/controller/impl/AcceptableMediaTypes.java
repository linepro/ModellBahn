package com.linepro.modellbahn.controller.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;

public interface AcceptableMediaTypes {

    String APPLICATION = "application";
    String IMAGE = "image";
    String TEXT = "text";

    String BMP = "bmp";
    String GIF = "gif";
    String JPEG = "jpeg";
    String JPG = "jpg";
    String MSWORD = "msword";
    String MSWRITE = "mswrite";
    String PDF = "pdf";
    String PICT = "pict";
    String PLAIN = "plain";
    String PNG = "png";
    String RICHTEXT = "richtext";
    String TIFF = "tiff";
    String X_PCX = "x-pcx";
    String X_PICT = "x-pict";

    MediaType BM_TYPE = new MediaType(IMAGE, BMP);
    MediaType BMP_TYPE = new MediaType(IMAGE, BMP);
    MediaType GIF_TYPE = new MediaType(IMAGE, GIF);
    MediaType JFIF_TYPE = new MediaType(IMAGE, JPEG);
    MediaType JPE_TYPE = new MediaType(IMAGE, JPEG);
    MediaType JPEG_TYPE = new MediaType(IMAGE, JPEG);
    MediaType JPG_TYPE = new MediaType(IMAGE, JPG);
    MediaType PCT_TYPE = new MediaType(IMAGE, X_PICT);
    MediaType PCX_TYPE = new MediaType(IMAGE, X_PCX);
    MediaType PIC_TYPE = new MediaType(IMAGE, PICT);
    MediaType PICT_TYPE = new MediaType(IMAGE, PICT);
    MediaType PNG_TYPE = new MediaType(IMAGE, PNG);
    MediaType TIF_TYPE = new MediaType(IMAGE, TIFF);
    MediaType TIFF_TYPE = new MediaType(IMAGE, TIFF);
    
    MediaType IDC_TYPE = new MediaType(TEXT, PLAIN);
    MediaType DOC_TYPE = new MediaType(APPLICATION, MSWORD);
    MediaType DOT_TYPE = new MediaType(APPLICATION, MSWORD);
    MediaType PDF_TYPE = new MediaType(APPLICATION, PDF);
    MediaType RT_TYPE = new MediaType(TEXT, RICHTEXT);
    MediaType RTF_TYPE = new MediaType(TEXT, RICHTEXT);
    MediaType RTX_TYPE = new MediaType(TEXT, RICHTEXT);
    MediaType TEXT_TYPE = new MediaType(TEXT, PLAIN);
    MediaType TXT_TYPE = new MediaType(TEXT, PLAIN);
    MediaType WRI_TYPE = new MediaType(APPLICATION, MSWRITE);

    List<MediaType> DOCUMENT_TYPES = Arrays.asList(PDF_TYPE, TEXT_TYPE);

    List<MediaType> IMAGE_TYPES = Arrays.asList(BMP_TYPE, GIF_TYPE, JPG_TYPE, JPEG_TYPE, PNG_TYPE);

}
