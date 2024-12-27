package com.spring_cloud_project.inventory_service.Repository;

import com.spring_cloud_project.inventory_service.Models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long> {

    @Query(value = "SELECT new com.spring_cloud_project.inventory_service.Models.Inventory(i.id,i.skuCode,i.quantity) from Inventory i where i.skuCode = :skuCode")
    Inventory getInventoryBySku(String skuCode);

}
