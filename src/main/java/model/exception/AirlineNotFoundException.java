package model.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AirlineNotFoundException extends RuntimeException {
    public AirlineNotFoundException() {
        this("Airline not found");
    }

    public AirlineNotFoundException(String message) {
        super(message);
    }

    public AirlineNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
