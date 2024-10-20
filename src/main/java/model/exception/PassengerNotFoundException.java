package model.exception;

import lombok.Data;

@Data
public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException() {
        this("Passenger not found");
    }

    public PassengerNotFoundException(String message) {
        super(message);
    }

    public PassengerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}