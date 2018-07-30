package com.linepro.modellbahn.rest.service;

import java.io.File;
import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.linepro.modellbahn.rest.util.AbstractService;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.util.StaticContentFinder;

@Path("/")
public class HttpService extends AbstractService {

    protected static final String WEB_ROOT = StringUtils.strip(ApiPaths.WEB_ROOT, "/");
    public HttpService() {
    }

    @GET
    @Path("{path: .*}")
    @Produces({MediaType.WILDCARD})
    public Response getFile() {
        URI requested = uriInfo.getBaseUri().relativize(uriInfo.getRequestUri());

        // We really really don't want leading or trailing slashes
        String stripedPath = StringUtils.strip(StringUtils.removeStart(StringUtils.strip(requested.getPath(), "/"), WEB_ROOT), "/");

        String path = StringUtils.isBlank(stripedPath) ? "index.html" :stripedPath;

        info("GET " + path);

        File file = StaticContentFinder.get().findFile(path);

        if (file != null) {
            return Response.ok(file).build();
        }

        return notFound().build();
    }
}
