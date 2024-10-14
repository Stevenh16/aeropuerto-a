package model.exception;

import lombok.Data;

@Data
public class FlightNotFoundException extends RuntimeException {
  public FlightNotFoundException() {
    this("Flight not found");
  }

  public FlightNotFoundException(String message) {
    super(message);
  }

  public FlightNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
