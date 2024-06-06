package dev.ayush.Splitwise.exception;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException() {
        super();
    }

    public InvalidCredentialException(String message) {
        super(message);
    }

    public InvalidCredentialException(String message, Throwable cause) {
        super(message, cause);
    }
}
