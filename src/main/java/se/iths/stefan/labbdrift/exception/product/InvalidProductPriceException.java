package se.iths.stefan.labbdrift.exception.product;

public class InvalidProductPriceException extends RuntimeException {
    public InvalidProductPriceException(String message) {
        super(message);
    }
}
