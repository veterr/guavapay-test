package org.guavapay.delivery.dto;

import org.guavapay.delivery.model.Status;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;
    private String name;
    private String destination;
    private Status status;
    private String details;
    private UserDto creator;
    private UserDto courier;

    private List<CoordinatesDto> coordinates;
}
