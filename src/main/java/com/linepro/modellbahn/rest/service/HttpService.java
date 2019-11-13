package com.linepro.modellbahn.rest.service;

import static com.linepro.modellbahn.rest.util.AcceptableMediaTypes.MEDIA_TYPES;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linepro.modellbahn.rest.util.AbstractService;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.util.StaticContentFinder;

@Controller
@RequestMapping(ApiPaths.WEB_ROOT)
public class HttpService extends AbstractService {

    protected final Logger logger;
    
    public HttpService() {
        super();

        this.logger = LoggerFactory.getILoggerFactory().getLogger(getClass().getName());
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @GetMapping(ApiPaths.WEB_PART)
    public ResponseEntity<?> getFile(@PathVariable(ApiPaths.PATH_PARAM_NAME) String path) {
        logGet(path);

        return handleRequest(path);
    }

    @PostMapping(ApiPaths.WEB_PART)
    public ResponseEntity<?> postFile(@PathVariable(ApiPaths.PATH_PARAM_NAME) String path) {
        logPost(path);
        
        return handleRequest(path);
    }

    private ResponseEntity<?> handleRequest(String path) {
        String requested = StringUtils.strip(path, ApiPaths.SEPARATOR);

        String filePath = StringUtils.isBlank(requested) ? "index.html" : requested;

        File file = StaticContentFinder.getFinder().findFile(filePath);

        if (file != null) {
            return ResponseEntity.ok().contentType(getMediaType(file)).body(file);
        }

        return notFound();
    }

    private String getExtension(File path) {
        String fileName = path.toString();
        String extension = null;

        int extensionStart = fileName.lastIndexOf('.');

        if (extensionStart >= 0) {
            extension = fileName.substring(extensionStart+1).toLowerCase();
        }

        return extension;
    }

    private MediaType getMediaType(File path) {
        MediaType mediaType = MEDIA_TYPES.get(getExtension(path));

        if (mediaType != null) {
            return mediaType;
        }

        return MediaType.APPLICATION_OCTET_STREAM;
    }
}
