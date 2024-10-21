package model.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReserveNotFoundException extends RuntimeException {
    public ReserveNotFoundException() {
        this("Reserve not found");
    }

    public ReserveNotFoundException(String message) {
        super(message);
    }

    public ReserveNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
