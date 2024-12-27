package com.spring_cloud_project.order_service.Service;

import com.spring_cloud_project.order_service.Dto.OrderRequest;
import com.spring_cloud_project.order_service.Models.Order;
import com.spring_cloud_project.order_service.Utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    ResponseEntity<ApiResponse<Order>> createOrder(OrderRequest orderRequest);
    ResponseEntity<ApiResponse<List<Order>>> getAllOrders();
    ResponseEntity<ApiResponse<Optional<Order>>> getOrderById(Long id);
    ResponseEntity<ApiResponse<Optional<Order>>> deleteOrderById(Long id);
    ResponseEntity<ApiResponse<Order>> updateOrder(Long id, OrderRequest orderRequest);
}
