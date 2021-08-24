package ua.com.vovacoffee.exception;

public class WrongInformationException extends RuntimeException {

    public WrongInformationException() {
        super();
    }

    public WrongInformationException(String message) {
        super(message);
    }
}
