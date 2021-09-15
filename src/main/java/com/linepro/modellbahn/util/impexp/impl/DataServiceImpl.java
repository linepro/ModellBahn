package com.linepro.modellbahn.util.impexp.impl;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.ApiMessages;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;
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
    public void exportCSV(String type, HttpServletResponse response, String charset) {
        try {
            response.setContentType(TEXT_CSV);
            response.setCharacterEncoding(charset);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"{}.csv\"", type));

            DataType dataType = DataType.fromTypeName(type);

            if (dataType == null) {
                throw ModellBahnException.raise(ApiMessages.EXPORT_NOT_SUPPORTED)
                                         .addValue(type)
                                         .setStatus(HttpStatus.BAD_REQUEST);
            }

            Exporter exporter = exporterFactory.getExporter(dataType);

            exporter.write(response.getWriter());
        } catch (ModellBahnException e) {
            throw e;
        } catch (Exception e) {
            throw ModellBahnException.raise(ApiMessages.EXPORT_ERROR)
                                     .addValue(type);
        }
    }

    @Override
    public void importCSV(String type, MultipartFile multipart, String charset) {
        try {
            DataType dataType = DataType.fromTypeName(type);

            if (dataType == null) {
                throw ModellBahnException.raise(ApiMessages.IMPORT_NOT_SUPPORTED)
                                         .addValue(type)
                                         .setStatus(HttpStatus.BAD_REQUEST);
            }

            Importer importer = importerFactory.getImporter(dataType);

            Reader in = new InputStreamReader(multipart.getInputStream(), charset);

            importer.read(in);
        } catch (ModellBahnException e) {
            throw e;
        } catch (Exception e) {
            throw ModellBahnException.raise(ApiMessages.IMPORT_ERROR)
                                     .addValue(type);
        }
    }
}
