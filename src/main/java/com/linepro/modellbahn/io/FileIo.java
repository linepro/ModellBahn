package com.linepro.modellbahn.io;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({
    FileServiceImpl.class,
    FileStoreImpl.class,
    FileUploadHandlerImpl.class,
    MvcConfig.class,
    ResourceEndpoints.class
})
@Component(PREFIX + "FileIo")
public class FileIo {
}
