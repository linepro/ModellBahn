package com.linepro.modellbahn.util.impexp;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface DataService {

    String TEXT_CSV = "text/csv";

    void exportCSV(String type, HttpServletResponse response);

    void importCSV(String type, MultipartFile multipart);
}
