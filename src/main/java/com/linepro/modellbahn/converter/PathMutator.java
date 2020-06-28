package com.linepro.modellbahn.converter;

import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.io.FileStore;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PathMutator implements Mutator<Path, String> {

    @Autowired
    private FileStore fileStore;

    @Override
    public String get() {
        return "";
    }

    @Override
    public String apply(Path source, String destination) {
        if (source != null) {
            return FilenameUtils.separatorsToUnix(fileStore.fileStoreRoot().relativize(source).toString());
        }

        return null;
    }
}
