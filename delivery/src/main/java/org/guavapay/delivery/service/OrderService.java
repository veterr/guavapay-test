package org.guavapay.delivery.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.guavapay.delivery.dto.CoordinatesDto;
import org.guavapay.delivery.dto.OrderDto;
import org.guavapay.delivery.dto.UserDto;
import org.guavapay.delivery.exception.DeliveryException;
import org.guavapay.delivery.mapper.AuthMapper;
import org.guavapay.delivery.mapper.DeliveryMapper;
import org.guavapay.delivery.model.*;
import org.guavapay.delivery.repository.OrderRepository;
import org.guavapay.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final UserRepository userRepository;
    private final DeliveryMapper mapper;
    private final AuthMapper authMapper;

    public void createOrder(OrderDto order) throws JsonProcessingException {
        repository.save(mapper.dtoToModel(order));
    }

    public void cancelOrder(UUID orderId) throws DeliveryException {
        Order order = findOrderById(orderId);
        if (order.getStatus() == Status.DONE) {
            throw new DeliveryException("Заказ невозможно отменить так как он уже домставлен!");
        } else if (order.getStatus() == Status.CANCELED) {
            throw new DeliveryException("Заказ уже отменен!");
        }
        order.setStatus(Status.CANCELED);
        repository.save(order);
    }

    public String getOrderDetails(UUID id) throws DeliveryException {
        return repository.findById(id).orElseThrow(() -> new DeliveryException(DeliveryException.NO_SUCH_ORDER)).getDetails();
    }

    public List<OrderDto> getOrdersByUser(UUID id) {
        return mapper.modelOrderListToDto(repository.findAllByCreatorId(id));
    }

    public void changeStatus(UUID orderId, Status newStatus) throws JsonProcessingException, DeliveryException {
        Order order = findOrderById(orderId);
        order.setStatus(newStatus);
        repository.save(order);
    }

    public void assignOrder(UUID orderId, UUID courierId) throws DeliveryException {
        Order order = findOrderById(orderId);
        // TODO Проверь отработает ли такое!
        order.setCourier(userRepository.getReferenceById(courierId));
    }

    public List<CoordinatesDto> getOrderCoordinates(UUID orderId) throws DeliveryException {
        return mapper.modelCoordinatesListToDto(findOrderById(orderId).getCoordinates());
    }

    public void updateOrderCoordinates(UUID orderId, CoordinatesDto coordinatesDto) throws DeliveryException, JsonProcessingException {
        Order order = findOrderById(orderId);
        order.getCoordinates().add(mapper.dtoToModel(coordinatesDto));
        repository.save(order);
    }

    public List<OrderDto> getAllOrders() {
        return mapper.modelOrderListToDto(repository.findAll());
    }

    public List<UserDto> getCourierListWithOrders() throws JsonProcessingException {
        List<User> couriers = userRepository.findAll().stream().filter(user -> user.getRoles().stream().map(
                Role::getName).collect(Collectors.toList()).contains(ERole.ROLE_COURIER.name())).collect(Collectors.toList());
        return authMapper.modelListToDto(couriers);
    }

    private Order findOrderById(UUID orderId) throws DeliveryException {
        return repository.findById(orderId).orElseThrow(()->new DeliveryException(DeliveryException.NO_SUCH_ORDER));
    }
}
