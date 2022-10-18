package org.guavapay.delivery.exception;

public class DeliveryException extends Exception {

    public static final String NO_SUCH_ORDER = "Такого заказа не существует!";

    public DeliveryException(String message) {
        super(message);
    }

    public DeliveryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeliveryException(Throwable cause) {
        super(cause);
    }
}
