package com.linepro.modellbahn.converter;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.io.FileStore;

import lombok.RequiredArgsConstructor;

@Component(PREFIX + "PathMutator")
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
            return fileStore.fileUrl(source);
        }

        return null;
    }
}
