package model.dto;

import java.util.List;

public record ClientIdDto(int id, String name, String lastname, String address, String cellphone, String email, List<ReserveIdDto> reserves) {
}
