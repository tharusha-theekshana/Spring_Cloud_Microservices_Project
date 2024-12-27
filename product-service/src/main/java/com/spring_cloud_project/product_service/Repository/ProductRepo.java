package com.spring_cloud_project.product_service.Repository;

import com.spring_cloud_project.product_service.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Product,String> {
}
