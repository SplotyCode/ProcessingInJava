package me.david.processinginjava.exception;

public class StartUpException extends RuntimeException {

    public StartUpException() {
    }

    public StartUpException(String message) {
        super(message);
    }

    public StartUpException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public StartUpException(Throwable throwable) {
        super(throwable);
    }


}
