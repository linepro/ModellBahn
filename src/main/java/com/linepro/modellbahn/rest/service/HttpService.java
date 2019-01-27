package com.linepro.modellbahn.rest.service;

import static com.linepro.modellbahn.rest.util.AcceptableMediaTypes.MEDIA_TYPES;

import java.io.File;
import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linepro.modellbahn.rest.util.AbstractService;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.util.StaticContentFinder;

@Path(ApiPaths.WEB_ROOT)
public class HttpService extends AbstractService {

    private static final String WEB_ROOT = StringUtils.strip(ApiPaths.WEB_ROOT, ApiPaths.SEPARATOR);

    protected final Logger logger;
    
    public HttpService() {
        super();

        this.logger = LoggerFactory.getILoggerFactory().getLogger(getClass().getName());
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @GET
    @Path(ApiPaths.WEB_PART)
    @Produces()
    public Response getFile() {
        return handleRequest();
    }

    @POST
    @Path(ApiPaths.WEB_PART)
    @Produces()
    public Response postFile() {
        return handleRequest();
    }

    private Response handleRequest() {
        URI requested = uriInfo.getBaseUri().relativize(uriInfo.getRequestUri());

        // We really really don't want leading or trailing slashes
        String stripedPath = StringUtils.strip(StringUtils.removeStart(StringUtils.strip(requested.getPath(), ApiPaths.SEPARATOR), WEB_ROOT), ApiPaths.SEPARATOR);

        String path = StringUtils.isBlank(stripedPath) ? "index.html" : stripedPath;

        logGet(path);

        File file = StaticContentFinder.getFinder().findFile(path);

        if (file != null) {
            return Response.ok(file).type(getMediaType(file)).build();
        }

        return notFound().build();
    }

    private String getExtension(File path) {
        String fileName = path.toString();
        String extension = null;

        int extensionStart = fileName.lastIndexOf(".");

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

        return MediaType.APPLICATION_OCTET_STREAM_TYPE;
    }
}
