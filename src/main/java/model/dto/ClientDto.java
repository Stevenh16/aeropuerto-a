package model.dto;

import model.entity.Role;

import java.util.List;
import java.util.Set;

public record ClientDto(int id, String username, String password, String name, String lastname, String address, String cellphone, String email, List<ReserveDto> reserves, Set<Role> roles) {
}
