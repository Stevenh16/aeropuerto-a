package model.exception;

import lombok.Data;

@Data
public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException() {
        this("Airport not found");
    }

    public AirportNotFoundException(String message) {
        super(message);
    }

    public AirportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
