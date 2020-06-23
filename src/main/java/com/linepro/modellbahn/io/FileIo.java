package com.linepro.modellbahn.io;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({
    FileServiceImpl.class,
    FileStoreImpl.class,
    FileUploadHandlerImpl.class,
    MvcConfig.class
})
@Component
public class FileIo {
}
