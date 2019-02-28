package com.linepro.modellbahn.rest.service;

import static com.linepro.modellbahn.rest.util.AcceptableMediaTypes.MEDIA_TYPES;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    public Response getFile(@PathParam(ApiPaths.PATH_PARAM_NAME) String path) {
        return handleRequest(path);
    }

    @POST
    @Path(ApiPaths.WEB_PART)
    @Produces()
    public Response postFile(@PathParam(ApiPaths.PATH_PARAM_NAME) String path) {
        return handleRequest(path);
    }

    private Response handleRequest(String path) {
        String requested = StringUtils.strip(path, ApiPaths.SEPARATOR);

        String filePath = StringUtils.isBlank(requested) ? "index.html" : requested;

        logGet(filePath);

        File file = StaticContentFinder.getFinder().findFile(filePath);

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
