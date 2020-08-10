package com.linepro.modellbahn.util.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ModellBahnException extends RuntimeException {

    private static final long serialVersionUID = 7743768225372892401L;

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    private final List<Object> values = new ArrayList<>();

    public ModellBahnException(String message) {
        super(message);
    }

    public ModellBahnException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ModellBahnException addValue(Object value) {
        values.add(value);
        return this;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ModellBahnException setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public Object[] getValues() {
        return values.toArray();
    }
    
    public static ModellBahnException raise(String message) {
        return new ModellBahnException(message);
    }
    
    public static ModellBahnException raise(String message, Throwable cause) {
        return new ModellBahnException(message, cause);
    }
}
