package org.app4_quilles.junit;

/**
 * Exception thrown when an async test encounters an issue.
 */
public class AsyncTestException extends RuntimeException {
    public AsyncTestException() {super();}
    public AsyncTestException(String msg) {super(msg);}
}

