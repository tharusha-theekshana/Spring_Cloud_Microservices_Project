package com.spring_cloud_project.product_service.Service.Impl;

import com.spring_cloud_project.product_service.Dto.ProductRequest;
import com.spring_cloud_project.product_service.Models.Product;
import com.spring_cloud_project.product_service.Repository.ProductRepo;
import com.spring_cloud_project.product_service.Service.ProductService;
import com.spring_cloud_project.product_service.Utils.ApiResponse;
import com.spring_cloud_project.product_service.Utils.ApiResponseGenerate;
import com.spring_cloud_project.product_service.Utils.ProductConstants;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Builder
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ResponseEntity<ApiResponse<Product>> createProduct(ProductRequest productRequest) {
        try {
            if (checkValidProduct(productRequest)){
                Product product = Product.builder()
                        .name(productRequest.getName())
                        .description(productRequest.getDescription())
                        .price(productRequest.getPrice())
                        .build();

                productRepo.save(product);
                return ApiResponseGenerate.createSuccessResponse(product, ProductConstants.PRODUCT_CREATED, HttpStatus.CREATED);
            }else{
                return ApiResponseGenerate.createErrorResponse(ProductConstants.INVALID_DATA.trim(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(ProductConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    boolean checkValidProduct(ProductRequest productRequest){
        if (productRequest.getName().isEmpty() || productRequest.getDescription().isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        try {
            List<Product> productList = productRepo.findAll();
            return ApiResponseGenerate.createSuccessResponseForList(productList, ProductConstants.DATA_FETCH, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponseForList(new ArrayList<>(), ProductConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse<Optional<Product>>> getProductById(String id) {
        try {
            Optional<Product> product = productRepo.findById(id);

            if (product.isEmpty()) {
                return ApiResponseGenerate.createErrorResponse(ProductConstants.PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND);
            } else {
                return ApiResponseGenerate.createSuccessResponse(product, ProductConstants.DATA_FETCH, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(ProductConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse<Optional<Product>>> deleteProductById(String id) {
        try {
            Optional<Product> product = productRepo.findById(id);

            if (product.isEmpty()) {
                return ApiResponseGenerate.createErrorResponse(ProductConstants.PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND);
            } else {
                productRepo.deleteById(id);
                return ApiResponseGenerate.createSuccessResponse(product, ProductConstants.PRODUCT_DELETED, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(ProductConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse<Product>> updateProduct(String id, ProductRequest productRequest) {
        try {
            Optional<Product> product = productRepo.findById(id);

            if (product.isEmpty()) {
                return ApiResponseGenerate.createErrorResponse(ProductConstants.PRODUCT_ID_NOT_FOUND, HttpStatus.NOT_FOUND);
            } else {
                product.get().setName(productRequest.getName());
                product.get().setDescription(productRequest.getDescription());
                product.get().setPrice(productRequest.getPrice());
                productRepo.save(product.get());

                return ApiResponseGenerate.createSuccessResponse(product.get(), ProductConstants.PRODUCT_UPDATED, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(ProductConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
