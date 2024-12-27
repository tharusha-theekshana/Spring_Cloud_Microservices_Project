package com.spring_cloud_project.product_service.Controllers;

import com.spring_cloud_project.product_service.Dto.ProductRequest;
import com.spring_cloud_project.product_service.Models.Product;
import com.spring_cloud_project.product_service.Service.ProductService;
import com.spring_cloud_project.product_service.Utils.ApiResponse;
import com.spring_cloud_project.product_service.Utils.ApiResponseGenerate;
import com.spring_cloud_project.product_service.Utils.ProductConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody ProductRequest productRequest){
        try{
            return productService.createProduct(productRequest);
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseGenerate.createErrorResponse(ProductConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>>  getAllProducts(){
        try{
            return productService.getAllProducts();
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseGenerate.createErrorResponseForList(null,ProductConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<Product>>> getProductById(@PathVariable("id") String id) {
        try{
            return productService.getProductById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(ProductConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<Product>>> deleteProductById(@PathVariable("id") String id) {
        try{
            return productService.deleteProductById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponseGenerate.createErrorResponse(ProductConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable("id") String id,@RequestBody ProductRequest productRequest){
        try{
            return productService.updateProduct(id,productRequest);
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseGenerate.createErrorResponse(ProductConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
