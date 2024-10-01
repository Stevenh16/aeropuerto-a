package model.dto;

public record PassengerIdDto(String name, String lastname, String address, String cellphone, String email, ReserveIdDto reserve) {
}
