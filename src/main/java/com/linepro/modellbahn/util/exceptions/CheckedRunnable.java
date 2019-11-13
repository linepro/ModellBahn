package com.linepro.modellbahn.util.exceptions;

public interface CheckedRunnable<E extends Throwable> {
    void run() throws E;
}