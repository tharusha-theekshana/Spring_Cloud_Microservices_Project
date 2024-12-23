package com.spring_cloud_project.product_service.Service;

import com.spring_cloud_project.product_service.Dto.ProductRequest;
import com.spring_cloud_project.product_service.Models.Product;
import com.spring_cloud_project.product_service.Utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<ApiResponse<Product>> createProduct(ProductRequest productRequest);
    ResponseEntity<ApiResponse<List<Product>>>  getAllProducts();
}
