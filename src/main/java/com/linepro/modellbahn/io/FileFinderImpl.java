package com.linepro.modellbahn.io;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.util.ToStringBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileFinderImpl implements FileFinder {


    @Value("${filestore.root:./}")
    private String fileRoot;

    @Value("${filestore.name:store}")
    private String storeFolder;

    @Value("'${filefinder.paths:./}'.split(',')")
    private Set<String> contentPaths;

    @Override
    public File findFile(String path) {
        for (String contentPath : contentPaths) {
            File file = Paths.get(contentPath).resolve(path).toFile();
            
            if (file.exists() && file.isFile() && file.canRead()) {
                log.info("Found: " + file);
    
                return file;
            }
        }

        return null;
    }

    @Override
    public Set<String> getAbsolutePaths() {
        Set<String> absolutePaths = new HashSet<>(contentPaths.size());
        
        for (String contentPath : contentPaths) {
            absolutePaths.add(Paths.get(contentPath).normalize().toAbsolutePath().toString());
        }
        
        return absolutePaths;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("contentPaths", getAbsolutePaths())
                .toString();
    }
}
