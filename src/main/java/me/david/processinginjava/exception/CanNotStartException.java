package me.david.processinginjava.exception;

public class CanNotStartException extends RuntimeException {

    public CanNotStartException() {
    }

    public CanNotStartException(String message) {
        super(message);
    }

    public CanNotStartException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CanNotStartException(Throwable throwable) {
        super(throwable);
    }

}
