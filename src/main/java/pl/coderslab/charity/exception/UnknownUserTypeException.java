package pl.coderslab.charity.exception;

public class UnknownUserTypeException extends RuntimeException{
    public UnknownUserTypeException(String message) {
        super(message);
    }
}
