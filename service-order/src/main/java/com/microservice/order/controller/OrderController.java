package com.microservice.order.controller;

import com.microservice.common.response.ApiResponse;
import com.microservice.order.entity.Order;
import com.microservice.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public ApiResponse<List<Order>> getAllOrders() {
        return ApiResponse.success("获取订单列表成功", orderRepository.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> ApiResponse.success("获取订单信息成功", order))
                .orElse(ApiResponse.error(404, "订单不存在"));
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        return ApiResponse.success("获取用户订单列表成功", orderRepository.findByUserId(userId));
    }

    @PostMapping
    public ApiResponse<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        return ApiResponse.success("订单创建成功", savedOrder);
    }

    @PutMapping("/{id}")
    public ApiResponse<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(orderDetails.getStatus());
                    order.setQuantity(orderDetails.getQuantity());
                    return ApiResponse.success("订单更新成功", orderRepository.save(order));
                })
                .orElse(ApiResponse.error(404, "订单不存在"));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteOrder(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ApiResponse.success("订单删除成功", null);
        }
        return ApiResponse.error(404, "订单不存在");
    }
}
