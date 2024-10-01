package model.dto;

public record PassengerDto(String name, String lastname, String address, String cellphone, String email, ReserveDto reserve) {
}
