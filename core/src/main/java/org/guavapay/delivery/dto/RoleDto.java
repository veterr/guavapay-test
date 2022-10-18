package org.guavapay.delivery.dto;

import org.guavapay.delivery.model.ERole;
import lombok.Data;

import java.util.UUID;

@Data
public class RoleDto {
    private UUID id;
    private ERole name;
}
