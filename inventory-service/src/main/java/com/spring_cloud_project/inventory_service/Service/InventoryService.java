package com.spring_cloud_project.inventory_service.Service;

import com.spring_cloud_project.inventory_service.Dto.InventoryRequest;
import com.spring_cloud_project.inventory_service.Models.Inventory;
import com.spring_cloud_project.order_service.Utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    ResponseEntity<ApiResponse<Inventory>> createInventory(InventoryRequest inventoryRequest);
    ResponseEntity<ApiResponse<List<Inventory>>> getAllInventories();
    ResponseEntity<ApiResponse<Inventory>> getInventoryBySkuCode(String skuCode);
    ResponseEntity<ApiResponse<Optional<Inventory>>> getInventoryById(Long id);
    ResponseEntity<ApiResponse<Inventory>> deleteInventoryBySkuCode(String skuCode);
    ResponseEntity<ApiResponse<Inventory>>  updateInventory(String skuCode, int quantity);
}
