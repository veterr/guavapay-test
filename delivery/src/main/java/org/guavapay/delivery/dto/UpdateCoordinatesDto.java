package org.guavapay.delivery.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateCoordinatesDto {
    UUID orderId;
    CoordinatesDto coordinatesDto;
}
