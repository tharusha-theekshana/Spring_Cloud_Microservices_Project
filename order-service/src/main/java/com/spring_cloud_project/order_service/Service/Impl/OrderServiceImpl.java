package com.spring_cloud_project.order_service.Service.Impl;

import com.spring_cloud_project.order_service.Dto.OrderRequest;
import com.spring_cloud_project.order_service.Models.Order;
import com.spring_cloud_project.order_service.Repository.OrderRepo;
import com.spring_cloud_project.order_service.Service.OrderService;
import com.spring_cloud_project.order_service.Utils.ApiResponse;
import com.spring_cloud_project.order_service.Utils.ApiResponseGenerate;
import com.spring_cloud_project.order_service.Utils.OrderConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepo orderRepo;

    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public ResponseEntity<ApiResponse<Order>> createOrder(OrderRequest orderRequest) {
        try{

            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.getSkuCode());
            order.setPrice(orderRequest.getPrice());
            order.setQuantity(orderRequest.getQuantity());

            orderRepo.save(order);
            return ApiResponseGenerate.createSuccessResponse(order,OrderConstants.ORDER_CREATED, HttpStatus.CREATED);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(OrderConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
        try{
            List<Order> orderList = orderRepo.findAll();
            return ApiResponseGenerate.createSuccessResponseForList(orderList,OrderConstants.DATA_FETCH, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(OrderConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
