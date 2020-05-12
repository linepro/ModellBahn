package com.linepro.modellbahn.controller.impl;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.MediaType;

public interface AcceptableMediaTypes {

    String AIF = "aif";
    String AIFC = "aifc";
    String AIFF = "aiff";
    String ASF = "asf";
    String ASX = "asx";
    String AU = "au";
    String AVI = "avi";
    String BM = "bm";
    String BMP = "bmp";
    String CER = "cer";
    String CLASS = "class";
    String CSH = "csh";
    String CSS = "css";
    String DER = "der";
    String DIF = "dif";
    String DL = "dl";
    String DOC = "doc";
    String DOT = "dot";
    String DV = "dv";
    String DWG = "dwg";
    String EOT = "eot";
    String EPS = "eps";
    String FLI = "fli";
    String FMF = "fmf";
    String FUNK = "funk";
    String GIF = "gif";
    String GL = "gl";
    String GSD = "gsd";
    String GSM = "gsm";
    String GZ = "gz";
    String GZIP = "gzip";
    String HTM = "htm";
    String HTML = "html";
    String HTMLS = "htmls";
    String HTT = "htt";
    String HTX = "htx";
    String ICO = "ico";
    String IDC = "idc";
    String INF = "inf";
    String ISU = "isu";
    String IT = "it";
    String JAM = "jam";
    String JAV = "jav";
    String JAVA = "java";
    String JFIF = "jfif";
    String JPE = "jpe";
    String JPEG = "jpeg";
    String JPG = "jpg";
    String JS = "js";
    String JSON = "json";
    String KAR = "kar";
    String KSH = "ksh";
    String LA = "la";
    String LMA = "lma";
    String LOG = "log";
    String M1V = "m1v";
    String M2A = "m2a";
    String M2V = "m2v";
    String M3U = "m3u";
    String MHT = "mht";
    String MHTML = "mhtml";
    String MID = "mid";
    String MIDI = "midi";
    String MIME = "mime";
    String MJPG = "mjpg";
    String MOOV = "moov";
    String MOV = "mov";
    String MP2 = "mp2";
    String MP3 = "mp3";
    String MPA = "mpa";
    String MPE = "mpe";
    String MPEG = "mpeg";
    String MPG = "mpg";
    String MPGA = "mpga";
    String OTF = "otf";
    String P10 = "p10";
    String P12 = "p12";
    String P7A = "p7a";
    String P7C = "p7c";
    String P7M = "p7m";
    String P7R = "p7r";
    String P7S = "p7s";
    String PART = "part";
    String PCT = "pct";
    String PCX = "pcx";
    String PDF = "pdf";
    String PIC = "pic";
    String PICT = "pict";
    String PL = "pl";
    String PNG = "png";
    String PS = "ps";
    String PY = "py";
    String QIF = "qif";
    String QT = "qt";
    String QTI = "qti";
    String QTIF = "qtif";
    String RT = "rt";
    String RTF = "rtf";
    String RTX = "rtx";
    String SDML = "sdml";
    String SGM = "sgm";
    String SGML = "sgml";
    String SH = "sh";
    String SHTML = "shtml";
    String SND = "snd";
    String SSI = "ssi";
    String SVG = "svg";
    String TCL = "tcl";
    String TCSH = "tcsh";
    String TEXT = "text";
    String TGZ = "tgz";
    String TIF = "tif";
    String TIFF = "tiff";
    String TSV = "tsv";
    String TTF = "ttf";
    String TXT = "txt";
    String VOC = "voc";
    String WAV = "wav";
    String WMF = "wmf";
    String WOFF = "woff";
    String WOFF2 = "woff2";
    String WRI = "wri";
    String XML = "xml";
    String Z = "z";
    String ZIP = "zip";
    String ZSH = "zsh";

    MediaType AIF_TYPE = new MediaType("audio", "aiff");
    MediaType AIFC_TYPE = new MediaType("audio", "aiff");
    MediaType AIFF_TYPE = new MediaType("audio", "aiff");
    MediaType ASF_TYPE = new MediaType("video", "x-ms-asf");
    MediaType ASX_TYPE = new MediaType("video", "x-ms-asf");
    MediaType AU_TYPE = new MediaType("audio", "basic");
    MediaType AVI_TYPE = new MediaType("video", "msvideo");
    MediaType BM_TYPE = new MediaType("image", "bmp");
    MediaType BMP_TYPE = new MediaType("image", "bmp");
    MediaType CER_TYPE = new MediaType("application", "x-x509-ca-cert");
    MediaType CLASS_TYPE = new MediaType("application", "java-byte-code");
    MediaType CSH_TYPE = new MediaType("application", "x-csh");
    MediaType CSS_TYPE = new MediaType("text", "css");
    MediaType DER_TYPE = new MediaType("application", "x-x509-ca-cert");
    MediaType DIF_TYPE = new MediaType("video", "x-dv");
    MediaType DL_TYPE = new MediaType("video", "dl");
    MediaType DOC_TYPE = new MediaType("application", "msword");
    MediaType DOT_TYPE = new MediaType("application", "msword");
    MediaType DV_TYPE = new MediaType("video", "x-dv");
    MediaType DWG_TYPE = new MediaType("image", "vnd.dwg");
    MediaType EOT_TYPE = new MediaType("application", "vnd.ms-fontobject");
    MediaType EPS_TYPE = new MediaType("application", "postscript");
    MediaType FLI_TYPE = new MediaType("video", "fli");
    MediaType FMF_TYPE = new MediaType("video", "x-atomic3d-feature");
    MediaType FUNK_TYPE = new MediaType("audio", "make");
    MediaType GIF_TYPE = new MediaType("image", "gif");
    MediaType GL_TYPE = new MediaType("video", "gl");
    MediaType GSD_TYPE = new MediaType("audio", "x-gsm");
    MediaType GSM_TYPE = new MediaType("audio", "x-gsm");
    MediaType GZ_TYPE = new MediaType("application", "x-gzip");
    MediaType GZIP_TYPE = new MediaType("application", "x-gzip");
    MediaType HTM_TYPE = new MediaType("text", "html");
    MediaType HTML_TYPE = new MediaType("text", "html");
    MediaType HTMLS_TYPE = new MediaType("text", "html");
    MediaType HTT_TYPE = new MediaType("text", "webviewhtml");
    MediaType HTX_TYPE = new MediaType("text", "html");
    MediaType ICO_TYPE = new MediaType("image", "x-icon");
    MediaType IDC_TYPE = new MediaType("text", "plain");
    MediaType INF_TYPE = new MediaType("application", "inf");
    MediaType ISU_TYPE = new MediaType("video", "x-isvideo");
    MediaType IT_TYPE = new MediaType("audio", "it");
    MediaType JAM_TYPE = new MediaType("audio", "x-jam");
    MediaType JAV_TYPE = new MediaType("text", "x-java-source");
    MediaType JAVA_TYPE = new MediaType("text", "x-java-source");
    MediaType JFIF_TYPE = new MediaType("image", "jpeg");
    MediaType JPE_TYPE = new MediaType("image", "jpeg");
    MediaType JPEG_TYPE = new MediaType("image", "jpeg");
    MediaType JPG_TYPE = new MediaType("image", "jpg");
    MediaType JS_TYPE = new MediaType("application", "javascript");
    MediaType JSON_TYPE = new MediaType("application", "json"); 
    MediaType KAR_TYPE = new MediaType("audio", "midi");
    MediaType KSH_TYPE = new MediaType("application", "x-ksh");
    MediaType LA_TYPE = new MediaType("audio", "nspaudio");
    MediaType LMA_TYPE = new MediaType("audio", "nspaudio");
    MediaType LOG_TYPE = new MediaType("text", "plain");
    MediaType M1V_TYPE = new MediaType("video", "mpeg");
    MediaType M2A_TYPE = new MediaType("audio", "mpeg");
    MediaType M2V_TYPE = new MediaType("video", "mpeg");
    MediaType M3U_TYPE = new MediaType("audio", "x-mpequrl");
    MediaType MHT_TYPE = new MediaType("message", "rfc822");
    MediaType MHTML_TYPE = new MediaType("message", "rfc822");
    MediaType MID_TYPE = new MediaType("audio", "midi");
    MediaType MIDI_TYPE = new MediaType("audio", "midi");
    MediaType MIME_TYPE = new MediaType("www", "mime");
    MediaType MJPG_TYPE = new MediaType("video", "x-motion-jpeg");
    MediaType MOOV_TYPE = new MediaType("video", "quicktime");
    MediaType MOV_TYPE = new MediaType("video", "quicktime");
    MediaType MP2_TYPE = new MediaType("video", "mpeg");
    MediaType MP3_TYPE = new MediaType("video", "mpeg");
    MediaType MPA_TYPE = new MediaType("video", "mpeg");
    MediaType MPE_TYPE = new MediaType("video", "mpeg");
    MediaType MPEG_TYPE = new MediaType("video", "mpeg");
    MediaType MPG_TYPE = new MediaType("video", "mpeg");
    MediaType MPGA_TYPE = new MediaType("audio", "mpeg");
    MediaType OTF_TYPE = new MediaType("application", "font-sfnt");
    MediaType P10_TYPE = new MediaType("application", "pkcs10");
    MediaType P12_TYPE = new MediaType("application", "pkcs-12");
    MediaType P7A_TYPE = new MediaType("application", "x-pkcs7-signature");
    MediaType P7C_TYPE = new MediaType("application", "pkcs7-mime");
    MediaType P7M_TYPE = new MediaType("application", "pkcs7-mime");
    MediaType P7R_TYPE = new MediaType("application", "x-pkcs7-certreqresp");
    MediaType P7S_TYPE = new MediaType("application", "pkcs7-signature");
    MediaType PART_TYPE = new MediaType("application", "pro_eng");
    MediaType PCT_TYPE = new MediaType("image", "x-pict");
    MediaType PCX_TYPE = new MediaType("image", "x-pcx");
    MediaType PDF_TYPE = new MediaType("application", "pdf");
    MediaType PIC_TYPE = new MediaType("image", "pict");
    MediaType PICT_TYPE = new MediaType("image", "pict");
    MediaType PL_TYPE = new MediaType("text", "x-script.perl");
    MediaType PNG_TYPE = new MediaType("image", "png");
    MediaType PS_TYPE = new MediaType("application", "postscript");
    MediaType PY_TYPE = new MediaType("text", "x-script.phyton");
    MediaType QIF_TYPE = new MediaType("image", "x-quicktime");
    MediaType QT_TYPE = new MediaType("video", "quicktime");
    MediaType QTI_TYPE = new MediaType("image", "x-quicktime");
    MediaType QTIF_TYPE = new MediaType("image", "x-quicktime");
    MediaType RT_TYPE = new MediaType("text", "richtext");
    MediaType RTF_TYPE = new MediaType("text", "richtext");
    MediaType RTX_TYPE = new MediaType("text", "richtext");
    MediaType SDML_TYPE = new MediaType("text", "plain");
    MediaType SGM_TYPE = new MediaType("text", "sgml");
    MediaType SGML_TYPE = new MediaType("text", "sgml");
    MediaType SH_TYPE = new MediaType("text", "x-script.sh");
    MediaType SHTML_TYPE = new MediaType("text", "html");
    MediaType SND_TYPE = new MediaType("audio", "basic");
    MediaType SSI_TYPE = new MediaType("text", "x-server-parsed-html");
    MediaType SVG_TYPE = new MediaType("image", "svg+xml");
    MediaType TCL_TYPE = new MediaType("text", "x-script.tcl");
    MediaType TCSH_TYPE = new MediaType("text", "x-script.tcsh");
    MediaType TEXT_TYPE = new MediaType("text", "plain");
    MediaType TGZ_TYPE = new MediaType("application", "x-compressed");
    MediaType TIF_TYPE = new MediaType("image", "tiff");
    MediaType TIFF_TYPE = new MediaType("image", "tiff");
    MediaType TSV_TYPE = new MediaType("text", "tab-separated-values");
    MediaType TTF_TYPE = new MediaType("application", "font-ttf");
    MediaType TXT_TYPE = new MediaType("text", "plain");
    MediaType VOC_TYPE = new MediaType("audio", "voc");
    MediaType WAV_TYPE = new MediaType("audio", "wav");
    MediaType WMF_TYPE = new MediaType("windows", "metafile");
    MediaType WOFF_TYPE = new MediaType("font", "woff");
    MediaType WOFF2_TYPE = new MediaType("font", "woff2");
    MediaType WRI_TYPE = new MediaType("application", "mswrite");
    MediaType XML_TYPE = new MediaType("text", "xml");
    MediaType Z_TYPE = new MediaType("application", "x-compressed");
    MediaType ZIP_TYPE = new MediaType("application", "x-compressed");
    MediaType ZSH_TYPE = new MediaType("text", "x-script.zsh ");

    List<MediaType> DOCUMENT_TYPES = Arrays.asList(PDF_TYPE, TEXT_TYPE);

    List<MediaType> IMAGE_TYPES = Arrays.asList(BMP_TYPE, GIF_TYPE, JPG_TYPE, JPEG_TYPE, PNG_TYPE);

    Map<String,MediaType> MEDIA_TYPES = Stream.of(
        new AbstractMap.SimpleEntry<>(AIF, AIF_TYPE),
        new AbstractMap.SimpleEntry<>(AIFC, AIFC_TYPE),
        new AbstractMap.SimpleEntry<>(AIFF, AIFF_TYPE),
        new AbstractMap.SimpleEntry<>(ASF, ASF_TYPE),
        new AbstractMap.SimpleEntry<>(ASX, ASX_TYPE),
        new AbstractMap.SimpleEntry<>(AU, AU_TYPE),
        new AbstractMap.SimpleEntry<>(AVI, AVI_TYPE),
        new AbstractMap.SimpleEntry<>(BM, BM_TYPE),
        new AbstractMap.SimpleEntry<>(BMP, BMP_TYPE),
        new AbstractMap.SimpleEntry<>(CER, CER_TYPE),
        new AbstractMap.SimpleEntry<>(CLASS, CLASS_TYPE),
        new AbstractMap.SimpleEntry<>(CSH, CSH_TYPE),
        new AbstractMap.SimpleEntry<>(CSS, CSS_TYPE),
        new AbstractMap.SimpleEntry<>(DER, DER_TYPE),
        new AbstractMap.SimpleEntry<>(DIF, DIF_TYPE),
        new AbstractMap.SimpleEntry<>(DL, DL_TYPE),
        new AbstractMap.SimpleEntry<>(DOC, DOC_TYPE),
        new AbstractMap.SimpleEntry<>(DOT, DOT_TYPE),
        new AbstractMap.SimpleEntry<>(DV, DV_TYPE),
        new AbstractMap.SimpleEntry<>(DWG, DWG_TYPE),
        new AbstractMap.SimpleEntry<>(EOT, EOT_TYPE),
        new AbstractMap.SimpleEntry<>(EPS, EPS_TYPE),
        new AbstractMap.SimpleEntry<>(FLI, FLI_TYPE),
        new AbstractMap.SimpleEntry<>(FMF, FMF_TYPE),
        new AbstractMap.SimpleEntry<>(FUNK, FUNK_TYPE),
        new AbstractMap.SimpleEntry<>(GIF, GIF_TYPE),
        new AbstractMap.SimpleEntry<>(GL, GL_TYPE),
        new AbstractMap.SimpleEntry<>(GSD, GSD_TYPE),
        new AbstractMap.SimpleEntry<>(GSM, GSM_TYPE),
        new AbstractMap.SimpleEntry<>(GZ, GZ_TYPE),
        new AbstractMap.SimpleEntry<>(GZIP, GZIP_TYPE),
        new AbstractMap.SimpleEntry<>(HTM, HTM_TYPE),
        new AbstractMap.SimpleEntry<>(HTML, HTML_TYPE),
        new AbstractMap.SimpleEntry<>(HTMLS, HTMLS_TYPE),
        new AbstractMap.SimpleEntry<>(HTT, HTT_TYPE),
        new AbstractMap.SimpleEntry<>(HTX, HTX_TYPE),
        new AbstractMap.SimpleEntry<>(ICO, ICO_TYPE),
        new AbstractMap.SimpleEntry<>(IDC, IDC_TYPE),
        new AbstractMap.SimpleEntry<>(INF, INF_TYPE),
        new AbstractMap.SimpleEntry<>(ISU, ISU_TYPE),
        new AbstractMap.SimpleEntry<>(IT, IT_TYPE),
        new AbstractMap.SimpleEntry<>(JAM, JAM_TYPE),
        new AbstractMap.SimpleEntry<>(JAV, JAV_TYPE),
        new AbstractMap.SimpleEntry<>(JAVA, JAVA_TYPE),
        new AbstractMap.SimpleEntry<>(JFIF, JFIF_TYPE),
        new AbstractMap.SimpleEntry<>(JPE, JPE_TYPE),
        new AbstractMap.SimpleEntry<>(JPEG, JPEG_TYPE),
        new AbstractMap.SimpleEntry<>(JPG, JPG_TYPE),
        new AbstractMap.SimpleEntry<>(JS, JS_TYPE),
        new AbstractMap.SimpleEntry<>(JSON, JSON_TYPE),
        new AbstractMap.SimpleEntry<>(KAR, KAR_TYPE),
        new AbstractMap.SimpleEntry<>(KSH, KSH_TYPE),
        new AbstractMap.SimpleEntry<>(LA, LA_TYPE),
        new AbstractMap.SimpleEntry<>(LMA, LMA_TYPE),
        new AbstractMap.SimpleEntry<>(LOG, LOG_TYPE),
        new AbstractMap.SimpleEntry<>(M1V, M1V_TYPE),
        new AbstractMap.SimpleEntry<>(M2A, M2A_TYPE),
        new AbstractMap.SimpleEntry<>(M2V, M2V_TYPE),
        new AbstractMap.SimpleEntry<>(M3U, M3U_TYPE),
        new AbstractMap.SimpleEntry<>(MHT, MHT_TYPE),
        new AbstractMap.SimpleEntry<>(MHTML, MHTML_TYPE),
        new AbstractMap.SimpleEntry<>(MID, MID_TYPE),
        new AbstractMap.SimpleEntry<>(MIDI, MIDI_TYPE),
        new AbstractMap.SimpleEntry<>(MIME, MIME_TYPE),
        new AbstractMap.SimpleEntry<>(MJPG, MJPG_TYPE),
        new AbstractMap.SimpleEntry<>(MOOV, MOOV_TYPE),
        new AbstractMap.SimpleEntry<>(MOV, MOV_TYPE),
        new AbstractMap.SimpleEntry<>(MP2, MP2_TYPE),
        new AbstractMap.SimpleEntry<>(MP3, MP3_TYPE),
        new AbstractMap.SimpleEntry<>(MPA, MPA_TYPE),
        new AbstractMap.SimpleEntry<>(MPE, MPE_TYPE),
        new AbstractMap.SimpleEntry<>(MPEG, MPEG_TYPE),
        new AbstractMap.SimpleEntry<>(MPG, MPG_TYPE),
        new AbstractMap.SimpleEntry<>(MPGA, MPGA_TYPE),
        new AbstractMap.SimpleEntry<>(OTF, OTF_TYPE),
        new AbstractMap.SimpleEntry<>(P10, P10_TYPE),
        new AbstractMap.SimpleEntry<>(P12, P12_TYPE),
        new AbstractMap.SimpleEntry<>(P7A, P7A_TYPE),
        new AbstractMap.SimpleEntry<>(P7C, P7C_TYPE),
        new AbstractMap.SimpleEntry<>(P7M, P7M_TYPE),
        new AbstractMap.SimpleEntry<>(P7R, P7R_TYPE),
        new AbstractMap.SimpleEntry<>(P7S, P7S_TYPE),
        new AbstractMap.SimpleEntry<>(PART, PART_TYPE),
        new AbstractMap.SimpleEntry<>(PCT, PCT_TYPE),
        new AbstractMap.SimpleEntry<>(PCX, PCX_TYPE),
        new AbstractMap.SimpleEntry<>(PDF, PDF_TYPE),
        new AbstractMap.SimpleEntry<>(PIC, PIC_TYPE),
        new AbstractMap.SimpleEntry<>(PICT, PICT_TYPE),
        new AbstractMap.SimpleEntry<>(PL, PL_TYPE),
        new AbstractMap.SimpleEntry<>(PNG, PNG_TYPE),
        new AbstractMap.SimpleEntry<>(PS, PS_TYPE),
        new AbstractMap.SimpleEntry<>(PY, PY_TYPE),
        new AbstractMap.SimpleEntry<>(QIF, QIF_TYPE),
        new AbstractMap.SimpleEntry<>(QT, QT_TYPE),
        new AbstractMap.SimpleEntry<>(QTI, QTI_TYPE),
        new AbstractMap.SimpleEntry<>(QTIF, QTIF_TYPE),
        new AbstractMap.SimpleEntry<>(RT, RT_TYPE),
        new AbstractMap.SimpleEntry<>(RTF, RTF_TYPE),
        new AbstractMap.SimpleEntry<>(RTX, RTX_TYPE),
        new AbstractMap.SimpleEntry<>(SDML, SDML_TYPE),
        new AbstractMap.SimpleEntry<>(SGM, SGM_TYPE),
        new AbstractMap.SimpleEntry<>(SGML, SGML_TYPE),
        new AbstractMap.SimpleEntry<>(SH, SH_TYPE),
        new AbstractMap.SimpleEntry<>(SHTML, SHTML_TYPE),
        new AbstractMap.SimpleEntry<>(SND, SND_TYPE),
        new AbstractMap.SimpleEntry<>(SSI, SSI_TYPE),
        new AbstractMap.SimpleEntry<>(SVG, SVG_TYPE),
        new AbstractMap.SimpleEntry<>(TCL, TCL_TYPE),
        new AbstractMap.SimpleEntry<>(TCSH, TCSH_TYPE),
        new AbstractMap.SimpleEntry<>(TEXT, TEXT_TYPE),
        new AbstractMap.SimpleEntry<>(TGZ, TGZ_TYPE),
        new AbstractMap.SimpleEntry<>(TIF, TIF_TYPE),
        new AbstractMap.SimpleEntry<>(TIFF, TIFF_TYPE),
        new AbstractMap.SimpleEntry<>(TSV, TSV_TYPE),
        new AbstractMap.SimpleEntry<>(TTF, TTF_TYPE),
        new AbstractMap.SimpleEntry<>(TXT, TXT_TYPE),
        new AbstractMap.SimpleEntry<>(VOC, VOC_TYPE),
        new AbstractMap.SimpleEntry<>(WAV, WAV_TYPE),
        new AbstractMap.SimpleEntry<>(WMF, WMF_TYPE),
        new AbstractMap.SimpleEntry<>(WOFF, WOFF_TYPE),
        new AbstractMap.SimpleEntry<>(WOFF2, WOFF2_TYPE),
        new AbstractMap.SimpleEntry<>(WRI, WRI_TYPE),
        new AbstractMap.SimpleEntry<>(XML, XML_TYPE),
        new AbstractMap.SimpleEntry<>(Z, Z_TYPE),
        new AbstractMap.SimpleEntry<>(ZIP, ZIP_TYPE),
        new AbstractMap.SimpleEntry<>(ZSH, ZSH_TYPE)
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

}
