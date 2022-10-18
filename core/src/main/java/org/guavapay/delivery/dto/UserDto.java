package org.guavapay.delivery.dto;

import lombok.Data;

import java.util.*;

@Data
public class UserDto {
    private UUID id;
    private String username;
    private String email;
    private String name;
    private String phone;
    private String password;

    private List<OrderDto> orders = new ArrayList<>();
    private Set<RoleDto> roles = new HashSet<>();
}
