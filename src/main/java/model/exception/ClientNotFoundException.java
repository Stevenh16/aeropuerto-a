package model.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClientNotFoundException extends RuntimeException {
  public ClientNotFoundException() {this("Client not found");}
  public ClientNotFoundException(String message) {
        super(message);
  }
  public ClientNotFoundException(String message, Throwable cause) {super(message, cause);}
}
