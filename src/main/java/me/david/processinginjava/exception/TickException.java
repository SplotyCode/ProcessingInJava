package me.david.processinginjava.exception;

public class TickException extends RuntimeException {

    public TickException() {
    }

    public TickException(String message) {
        super(message);
    }

    public TickException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public TickException(Throwable throwable) {
        super(throwable);
    }

}
