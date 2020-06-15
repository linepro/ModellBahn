package com.linepro.modellbahn.util.impexp;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface DataService {

    String TEXT_CSV = "text/csv";

    void exportCSV(DataType type, HttpServletResponse response) throws Exception;

    void importCSV(DataType type, MultipartFile multipart) throws Exception;
}
