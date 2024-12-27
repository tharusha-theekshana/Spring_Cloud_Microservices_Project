package com.spring_cloud_project.order_service.Controllers;

import com.spring_cloud_project.order_service.Dto.OrderRequest;
import com.spring_cloud_project.order_service.Models.Order;
import com.spring_cloud_project.order_service.Service.OrderService;
import com.spring_cloud_project.order_service.Utils.ApiResponse;
import com.spring_cloud_project.order_service.Utils.ApiResponseGenerate;
import com.spring_cloud_project.order_service.Utils.OrderConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>>  getAllOrders(){
        try{
            return orderService.getAllOrders();
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseGenerate.createErrorResponseForList(null,OrderConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<Order>>> getOrderById(@PathVariable("id") Long id) {
        try{
            return orderService.getOrderById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(OrderConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<Order>>> deleteOrderById(@PathVariable("id") Long id) {
        try{
            return orderService.deleteOrderById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(OrderConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> updateOrder(@PathVariable("id") Long id,@RequestBody OrderRequest orderRequest){
        try{
            return orderService.updateOrder(id,orderRequest);
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseGenerate.createErrorResponse(OrderConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
