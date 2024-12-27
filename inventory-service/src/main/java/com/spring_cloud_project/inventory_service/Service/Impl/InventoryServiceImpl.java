package com.spring_cloud_project.inventory_service.Service.Impl;

import com.spring_cloud_project.inventory_service.Dto.InventoryRequest;
import com.spring_cloud_project.inventory_service.Models.Inventory;
import com.spring_cloud_project.inventory_service.Repository.InventoryRepo;
import com.spring_cloud_project.inventory_service.Service.InventoryService;
import com.spring_cloud_project.inventory_service.Utils.InventoryConstants;
import com.spring_cloud_project.order_service.Utils.ApiResponse;
import com.spring_cloud_project.order_service.Utils.ApiResponseGenerate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepo inventoryRepo;

    public InventoryServiceImpl(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }


    @Override
    public ResponseEntity<ApiResponse<Inventory>> createInventory(InventoryRequest inventoryRequest) {
        try{
            Inventory exist_inventory = inventoryRepo.getInventoryBySku(inventoryRequest.getSkuCode());

            if (exist_inventory == null){
                Inventory inventory =  new Inventory();

                if (inventoryRequest.getSkuCode().isEmpty()){
                    return ApiResponseGenerate.createErrorResponse(InventoryConstants.SKU_REQUIRED, HttpStatus.BAD_REQUEST);
                }

                if (inventoryRequest.getQuantity() <= 0){
                    return ApiResponseGenerate.createErrorResponse(InventoryConstants.QTY_REQUIRED, HttpStatus.BAD_REQUEST);
                }

                inventory.setSkuCode(inventoryRequest.getSkuCode());
                inventory.setQuantity(inventoryRequest.getQuantity());
                inventoryRepo.save(inventory);

                return ApiResponseGenerate.createSuccessResponseForList(inventory, InventoryConstants.INVENTORY_CREATED, HttpStatus.CREATED);
            }else{
                return ApiResponseGenerate.createErrorResponseForList(exist_inventory, InventoryConstants.INVENTORY_EXISTS, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(InventoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse<List<Inventory>>> getAllInventories() {
        try{
            List<Inventory> inventoryList = inventoryRepo.findAll();
            return ApiResponseGenerate.createSuccessResponseForList(inventoryList,InventoryConstants.DATA_FETCH, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(InventoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse<Inventory>> getInventoryBySkuCode(String skuCode) {
        try{
            Inventory inventory = inventoryRepo.getInventoryBySku(skuCode);

            if (inventory != null){
                return ApiResponseGenerate.createSuccessResponseForList(inventory,InventoryConstants.DATA_FETCH, HttpStatus.OK);
            }else{
                return ApiResponseGenerate.createErrorResponse(InventoryConstants.SKU_ID_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(InventoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse<Optional<Inventory>>> getInventoryById(Long id) {
        try{
            Optional<Inventory> inventory = inventoryRepo.findById(id);

            if (!inventory.isEmpty()){
                return ApiResponseGenerate.createSuccessResponseForList(inventory,InventoryConstants.DATA_FETCH, HttpStatus.OK);
            }else{
                return ApiResponseGenerate.createErrorResponse(InventoryConstants.INVENTORY_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(InventoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse<Inventory>> deleteInventoryBySkuCode(String skuCode) {
        try{
            Inventory inventory = inventoryRepo.getInventoryBySku(skuCode);

            if (inventory != null){
                inventoryRepo.deleteById(inventory.getId());
                return ApiResponseGenerate.createSuccessResponseForList(inventory,InventoryConstants.INVENTORY_DELETED, HttpStatus.OK);
            }else{
                return ApiResponseGenerate.createErrorResponse(InventoryConstants.SKU_ID_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(InventoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse<Inventory>> updateInventory(String skuCode, int quantity) {
        try{
            Inventory inventory = inventoryRepo.getInventoryBySku(skuCode);

            if (inventory != null){
                if (quantity <= 10){
                    return ApiResponseGenerate.createErrorResponse(InventoryConstants.QTY_REQUIRED, HttpStatus.BAD_REQUEST);
                }

                inventory.setQuantity(quantity);
                inventoryRepo.save(inventory);
                return ApiResponseGenerate.createSuccessResponseForList(inventory,InventoryConstants.INVENTORY_UPDATED, HttpStatus.OK);

            }else{
                return ApiResponseGenerate.createErrorResponse(InventoryConstants.SKU_ID_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(InventoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
