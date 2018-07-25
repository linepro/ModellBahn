package com.linepro.modellbahn.rest;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.linepro.modellbahn.rest.util.ApiPaths;

@Path(ApiPaths.WEB_ROOT)
public class HttpService {

    protected static final String WEB_ROOT = ApiPaths.WEB_ROOT.substring(1) + "/";

    protected final Logger logger;

    /** The uri info injected by Jersey. */
    @Context
    protected UriInfo uriInfo;

    protected static Set<String> contentPaths = Collections.synchronizedSet(new HashSet<String>());
    
    public static void addPath(String path) {
        contentPaths.add(path);
    }

    public static void addPaths(Collection<String> paths) {
        contentPaths.addAll(paths);
    }

    public static Set<String> getPaths() {
        return contentPaths;
    }
    
    public HttpService() {
        this.logger = Logger.getLogger("ModellBahn");
    }

    @GET
    @Path("{docPath:.*}")
    @Produces({MediaType.WILDCARD})
    public Response getFolder(@PathParam("docPath") String docPath) {
        URI requested = uriInfo.getBaseUri().relativize(uriInfo.getRequestUri());

        logger.info("Requested " + requested.getPath());

        java.nio.file.Path path;
        
        if (WEB_ROOT.equals(requested.getPath())) {
            path = Paths.get("index.html");
        } else {
            path = Paths.get(requested);
        }

        logger.info("Serving " + path);

        for (String contentPath : getPaths()) {
            File file = Paths.get(contentPath).resolve(path).toFile();
            
            if (file.exists() && file.isFile() && file.canRead()) {
                logger.info("Found" + file);

                return Response.ok(file).build();
            }
        }

        return Response.noContent().build();
    }
}
