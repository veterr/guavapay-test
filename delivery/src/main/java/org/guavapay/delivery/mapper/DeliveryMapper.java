package org.guavapay.delivery.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.guavapay.delivery.dto.CoordinatesDto;
import org.guavapay.delivery.dto.OrderDto;
import org.guavapay.delivery.model.Coordinates;
import org.guavapay.delivery.model.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DeliveryMapper {

    Order dtoToModel(OrderDto orderDto) throws JsonProcessingException;

    OrderDto modelToDto(Order order) throws JsonProcessingException;

    Coordinates dtoToModel(CoordinatesDto CoordinatesDto) throws JsonProcessingException;

    CoordinatesDto modelToDto(Coordinates Coordinates) throws JsonProcessingException;

    List<OrderDto> modelOrderListToDto(List<Order> orders);

    List<CoordinatesDto> modelCoordinatesListToDto(List<Coordinates> orders);

}
