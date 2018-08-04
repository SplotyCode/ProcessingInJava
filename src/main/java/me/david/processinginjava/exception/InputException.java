package me.david.processinginjava.exception;

public class InputException extends RuntimeException {

    public InputException() {
    }

    public InputException(String message) {
        super(message);
    }

    public InputException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InputException(Throwable throwable) {
        super(throwable);
    }

}
