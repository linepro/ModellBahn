package com.linepro.modellbahn.util.impexp.impl;

import java.io.InputStreamReader;
import java.io.Reader;

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
@Service("DataServiceImpl")
public class DataServiceImpl implements DataService {

    @Autowired
    private final ExporterFactory exporterFactory;

    @Autowired
    private final ImporterFactory importerFactory;
    
    @Override
    public void exportCSV(String type, HttpServletResponse response) throws Exception {
        response.setContentType(TEXT_CSV);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"{}.csv\"", type));

        Exporter exporter = exporterFactory.getExporter(DataType.fromTypeName(type));

        exporter.write(response.getWriter());
    }

    @Override
    public void importCSV(String type, MultipartFile multipart) throws Exception {
        Importer importer = importerFactory.getImporter(DataType.fromTypeName(type));

        Reader in = new InputStreamReader(multipart.getInputStream());

        importer.read(in);
    }
}
