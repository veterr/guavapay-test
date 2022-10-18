package org.guavapay.delivery.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CoordinatesDto {
    private UUID id;
    private UUID executionId;
    public Long latitude;
    public Long longitude;
    public Date dateTime;
}
