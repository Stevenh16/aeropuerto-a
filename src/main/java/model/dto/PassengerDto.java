package model.dto;

public record PassengerDto(int id, String name, String lastname, String address, String cellphone, String email, ReserveDto reserve) {
}
