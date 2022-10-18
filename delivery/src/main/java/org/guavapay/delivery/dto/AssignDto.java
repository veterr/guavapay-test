package org.guavapay.delivery.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AssignDto {
    UUID orderId;
    UUID courierId;
}
