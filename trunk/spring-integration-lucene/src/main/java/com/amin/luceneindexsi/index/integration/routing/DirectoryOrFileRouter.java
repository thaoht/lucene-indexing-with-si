package com.amin.luceneindexsi.index.integration.routing;

import org.apache.log4j.Logger;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class DirectoryOrFileRouter {
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    private String directoryRoute;
    private String fileRoute;

    public String isDirectory(URI uri){
        LOGGER.debug("inside DirectoryOrFileRouter");
        if(!uri.isAbsolute()){
            uri = assumeFile(uri);
        }
        Boolean isDirectory = new File(uri).isDirectory();
        LOGGER.debug("URI: "+uri+" is a "+
                (isDirectory ? "directory" : "file"));
        return isDirectory ? getDirectoryRoute() : getFileRoute();
    }

    private URI assumeFile(URI uri) {
        URI absolute;
        try{
            absolute = new URI("file:",
                    uri.getUserInfo(),
                    uri.getHost(),
                    uri.getPort(),
                    uri.getPath(),
                    uri.getQuery(),
                    uri.getFragment());
            return absolute;
        }catch (URISyntaxException e){
            throw new RuntimeException("couldn't use non absolute URI: "+uri, e);
        }
    }


    public String getDirectoryRoute() {
        return directoryRoute;
    }

    public String getFileRoute() {
        return fileRoute;
    }

    public void setDirectoryRoute(String directoryRoute) {
        this.directoryRoute = directoryRoute;
    }

    public void setFileRoute(String fileRoute) {
        this.fileRoute = fileRoute;
    }
}
