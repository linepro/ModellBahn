package com.linepro.modellbahn.util.impexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Base64Codec {

    private static final String BASE64 = "base64";
    private static final String CHARSET = "charset";
    private static final String DATA = "data";
    private static final String TYPE = "type";

    private static final String DATA_FORMAT = "^data:(?<" +TYPE+">\\w+\\/\\w+){1}(;charset=(?<"+CHARSET+">[^;,]+)){0,1}(?<"+BASE64+">;base64){0,1},(?<"+DATA+">.*)$";

    private Pattern pattern;
    
    @PostConstruct
    public void initialize() {
        pattern = Pattern.compile(DATA_FORMAT);
    }

    @SuppressWarnings("unused")
    public void handle(String path) {
        Matcher matcher = pattern.matcher(path);

        if (matcher.matches()) {
            boolean base64 = matcher.group(BASE64) != null;
            String mimeType = matcher.group(TYPE);
            String charset = matcher.group(CHARSET);
            String data = matcher.group(DATA);

            // TODO: parse Base64 and save data file.
        }
    }

}
