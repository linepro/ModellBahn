package com.linepro.modellbahn.persistence;

public interface IIdGenerator {

    String getNextId(String entityName, String keyName);
}
