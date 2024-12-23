package com.spring_cloud_project.order_service.Controllers;

import com.spring_cloud_project.order_service.Dto.OrderRequest;
import com.spring_cloud_project.order_service.Models.Order;
import com.spring_cloud_project.order_service.Service.OrderService;
import com.spring_cloud_project.order_service.Utils.ApiResponse;
import com.spring_cloud_project.order_service.Utils.ApiResponseGenerate;
import com.spring_cloud_project.order_service.Utils.OrderConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createProduct(@RequestBody OrderRequest orderRequest){
        try{
            return orderService.createOrder(orderRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(OrderConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
