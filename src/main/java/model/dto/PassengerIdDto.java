package model.dto;

public record PassengerIdDto(int id, String name, String lastname, String address, String cellphone, String email, ReserveIdDto reserve) {
}
