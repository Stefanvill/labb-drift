package se.iths.stefan.labbdrift.exception.product;

public class InvalidProductNameException extends RuntimeException {
    public InvalidProductNameException(String message) {
        super(message);
    }
}
