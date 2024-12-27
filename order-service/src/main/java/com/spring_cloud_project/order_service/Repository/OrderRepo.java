package com.spring_cloud_project.order_service.Repository;

import com.spring_cloud_project.order_service.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
}
