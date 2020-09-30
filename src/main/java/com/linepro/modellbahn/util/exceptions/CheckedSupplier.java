package com.linepro.modellbahn.util.exceptions;

@FunctionalInterface
public interface CheckedSupplier<V, E extends Throwable> {
    V get() throws E;
}