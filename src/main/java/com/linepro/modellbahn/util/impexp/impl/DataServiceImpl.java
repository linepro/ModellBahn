package com.linepro.modellbahn.util.impexp.impl;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.util.impexp.DataService;
import com.linepro.modellbahn.util.impexp.DataType;
import com.linepro.modellbahn.util.impexp.Exporter;
import com.linepro.modellbahn.util.impexp.ExporterFactory;
import com.linepro.modellbahn.util.impexp.Importer;
import com.linepro.modellbahn.util.impexp.ImporterFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service(PREFIX + "DataServiceImpl")
public class DataServiceImpl implements DataService {

    @Autowired
    private final ExporterFactory exporterFactory;

    @Autowired
    private final ImporterFactory importerFactory;
    
    @Override
    public void exportCSV(String type, HttpServletResponse response) throws Exception {
        response.setContentType(TEXT_CSV);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"{}.csv\"", type));

        DataType dataType = DataType.fromTypeName(type);

        if (dataType == null) {
            throw new IllegalArgumentException("Export of '" + type + "'is not supported");
        }

        Exporter exporter = exporterFactory.getExporter(dataType);

        exporter.write(response.getWriter());
    }

    @Override
    public void importCSV(String type, MultipartFile multipart) throws Exception {
        DataType dataType = DataType.fromTypeName(type);

        if (dataType == null) {
            throw new IllegalArgumentException("Import of '" + type + "'is not supported");
        }

        Importer importer = importerFactory.getImporter(dataType);

        Reader in = new InputStreamReader(multipart.getInputStream());

        importer.read(in);
    }
}
