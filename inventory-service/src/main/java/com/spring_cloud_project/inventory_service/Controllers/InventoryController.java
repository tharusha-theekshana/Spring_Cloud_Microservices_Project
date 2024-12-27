package com.spring_cloud_project.inventory_service.Controllers;

import com.spring_cloud_project.inventory_service.Dto.InventoryRequest;
import com.spring_cloud_project.inventory_service.Models.Inventory;
import com.spring_cloud_project.inventory_service.Service.InventoryService;
import com.spring_cloud_project.order_service.Utils.ApiResponse;
import com.spring_cloud_project.order_service.Utils.ApiResponseGenerate;
import com.spring_cloud_project.order_service.Utils.OrderConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Inventory>> createInventory(@RequestBody InventoryRequest inventoryRequest){
        try{
            return inventoryService.createInventory(inventoryRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(OrderConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Inventory>>>  getAllInventories(){
        try{
            return inventoryService.getAllInventories();
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseGenerate.createErrorResponseForList(null,OrderConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<Inventory>>> getInventoryById(@PathVariable("id") Long id) {
        try{
            return inventoryService.getInventoryById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(OrderConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/sku/{skucode}")
    public ResponseEntity<ApiResponse<Inventory>> getInventoryBySkuCode(@PathVariable("skucode") String skuCode) {
        try{
            return inventoryService.getInventoryBySkuCode(skuCode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(OrderConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{skucode}")
    public ResponseEntity<ApiResponse<Inventory>> deleteInventoryBySkuCode(@PathVariable("skucode") String skuCode) {
        try{
            return inventoryService.deleteInventoryBySkuCode(skuCode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(OrderConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{skucode}")
    public ResponseEntity<ApiResponse<Inventory>> updateInventory(@PathVariable("skucode") String skuCode,@RequestParam("quantity") int quantity){
        try{
            return inventoryService.updateInventory(skuCode,quantity);
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseGenerate.createErrorResponse(OrderConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
