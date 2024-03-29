package com.linepro.modellbahn.converter;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.io.FileStore;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "PathMapper")
public class PathMapper implements Mapper<Path, String> {

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
