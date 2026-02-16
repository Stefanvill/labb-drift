package se.iths.stefan.labbdrift.exception.product;

public class InvalidProductStockException extends RuntimeException {
    public InvalidProductStockException(String message) {
        super(message);
    }
}
