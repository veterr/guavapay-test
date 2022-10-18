package org.guavapay.delivery.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.guavapay.delivery.dto.RoleDto;
import org.guavapay.delivery.dto.UserDto;
import org.guavapay.delivery.model.Role;
import org.guavapay.delivery.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AuthMapper {
    Role dtoToModel(RoleDto RoleDto) throws JsonProcessingException;

    RoleDto modelToDto(Role Role) throws JsonProcessingException;

    User dtoToModel(UserDto UserDto) throws JsonProcessingException;

    UserDto modelToDto(User User) throws JsonProcessingException;

    List<UserDto> modelListToDto(List<User> User) throws JsonProcessingException;
}
