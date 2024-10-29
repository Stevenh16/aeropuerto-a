package model.mapper;

import model.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "id", ignore = true)
    Role toRole(String role);
    @Mapping(target = "id", ignore = true)
    Set<Role> toRoles(Set<String> roles);
}
