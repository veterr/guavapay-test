package org.guavapay.delivery.dto;

import org.guavapay.delivery.model.Status;
import lombok.Data;

import java.util.UUID;

@Data
public class StatusDto {

    UUID orderId;
    Status newStatus;
}
