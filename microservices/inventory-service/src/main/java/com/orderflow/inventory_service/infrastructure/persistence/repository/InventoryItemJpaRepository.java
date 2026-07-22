package com.orderflow.inventory_service.infrastructure.persistence.repository;

import com.orderflow.inventory_service.infrastructure.persistence.entity.InventoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryItemJpaRepository extends JpaRepository<InventoryItemEntity, UUID> {
    Optional<InventoryItemEntity> findByProductCode(String productCode);
}
