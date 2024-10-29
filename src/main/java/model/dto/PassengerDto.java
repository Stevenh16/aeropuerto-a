package model.dto;

import model.entity.Role;

import java.util.Set;

public record PassengerDto(int id, String name, String lastname, String address, String cellphone, String email, ReserveDto reserve) {
}
