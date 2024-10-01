package model.dto;

import java.util.List;

public record ClientDto(String name, String lastname, String address, String cellphone, String email, List<ReserveDto> reserves) {
}
