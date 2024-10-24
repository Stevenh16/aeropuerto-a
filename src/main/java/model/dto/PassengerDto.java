package model.dto;

import model.entity.Role;

import java.util.Set;

public record PassengerDto(int id, String name, String username, String password, String lastname, String address, String cellphone, String email, ReserveDto reserve, Set<Role> roles) {
}
