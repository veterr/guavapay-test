package org.guavapay.delivery.rest;

import org.guavapay.delivery.dto.*;
import org.guavapay.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Log
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final static String SUCCESS = "Success!";

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto order) {
        try {
            orderService.createOrder(order);
            return ResponseEntity.ok(SUCCESS);
        } catch (Exception ex) {
            return getErrorResponse(ex);
        }
    }

    @GetMapping("/cancel")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> cancelOrder(UUID orderId) {
        try {
            orderService.cancelOrder(orderId);
            return ResponseEntity.ok(SUCCESS);
        } catch (Exception ex) {
            return getErrorResponse(ex);
        }
    }

    @GetMapping("/details")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getOrderDetails(UUID id) {
        try {
            String details = orderService.getOrderDetails(id);
            return ResponseEntity.ok().body(details);
        } catch (Exception ex) {
            return getErrorResponse(ex);
        }
    }

    @PostMapping("/myOrders")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getOrdersByUser(UUID id) {
        try {
            List<OrderDto> orders = orderService.getOrdersByUser(id);
            return ResponseEntity.ok().body(orders);
        } catch (Exception ex) {
            return getErrorResponse(ex);
        }
    }

    @PostMapping("/changeStatus")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COURIER')")
    public ResponseEntity<?> changeStatus(@Valid @RequestBody StatusDto status) {
        try {
            orderService.changeStatus(status.getOrderId(), status.getNewStatus());
            return ResponseEntity.ok(SUCCESS);
        } catch (Exception ex) {
            return getErrorResponse(ex);
        }
    }

    @PostMapping("/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignOrder(@Valid @RequestBody AssignDto dto) {
        try {
            orderService.assignOrder(dto.getOrderId(), dto.getCourierId());
            return ResponseEntity.ok(SUCCESS);
        } catch (Exception ex) {
            return getErrorResponse(ex);
        }
    }

    @GetMapping("/getall")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllOrders() {
        try {
            return ResponseEntity.ok().body(orderService.getAllOrders());
        } catch (Exception ex) {
            return getErrorResponse(ex);
        }
    }

    @GetMapping("/getCoordinates")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOrderCoordinates(UUID orderId) {
        try {
            List<CoordinatesDto> coords = orderService.getOrderCoordinates(orderId);
            return ResponseEntity.ok().body(coords);
        } catch (Exception ex) {
            return getErrorResponse(ex);
        }
    }

    @GetMapping("/getCourierOrders")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCourierListWithOrders() {
        try {
            return ResponseEntity.ok().body(orderService.getCourierListWithOrders());
        } catch (Exception ex) {
            return getErrorResponse(ex);
        }
    }

    @PostMapping("/updateCoordinates")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COURIER')")
    public ResponseEntity<?> updateOrderCoordinates(@Valid @RequestBody UpdateCoordinatesDto dto) {
        try {
            orderService.updateOrderCoordinates(dto.getOrderId(), dto.getCoordinatesDto());
            return ResponseEntity.ok(SUCCESS);
        } catch (Exception ex) {
            return getErrorResponse(ex);
        }
    }

    @GetMapping("/courierOnlyTest")
    @PreAuthorize("hasRole('COURIER')")
    public ResponseEntity<?> courierOnlyTest() {
        try {
            return ResponseEntity.ok(SUCCESS);
        } catch (Exception ex) {
            return getErrorResponse(ex);
        }
    }

    private ResponseEntity<String> getErrorResponse(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getLocalizedMessage());
    }
}
