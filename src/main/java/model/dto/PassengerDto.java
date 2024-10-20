package model.dto;

public record PassengerDto(int id, String name, String username, String password, String lastname, String address, String cellphone, String email, ReserveDto reserve) {
}
