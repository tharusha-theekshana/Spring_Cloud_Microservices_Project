package com.spring_cloud_project.product_service.Service;

import com.spring_cloud_project.product_service.Dto.ProductRequest;
import com.spring_cloud_project.product_service.Models.Product;
import com.spring_cloud_project.product_service.Utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ResponseEntity<ApiResponse<Product>> createProduct(ProductRequest productRequest);
    ResponseEntity<ApiResponse<List<Product>>>  getAllProducts();
    ResponseEntity<ApiResponse<Optional<Product>>> getProductById(String id);
    ResponseEntity<ApiResponse<Optional<Product>>> deleteProductById(String id);
    ResponseEntity<ApiResponse<Product>> updateProduct(String id, ProductRequest productRequest);
}
