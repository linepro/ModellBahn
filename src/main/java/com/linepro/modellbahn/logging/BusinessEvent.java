package com.linepro.modellbahn.logging;

/**
 * Business events are all emitted on logger BUSINESS_LOG_ROOT with the sub logger of the event name.
 */
public enum BusinessEvent {

    GENERIC;

    public static final String BUSINESS_LOG_ROOT = "com.linepro.business";

    public String getLogCategory() {
        return String.join(".", BUSINESS_LOG_ROOT, this.name().toLowerCase());
    }
}
