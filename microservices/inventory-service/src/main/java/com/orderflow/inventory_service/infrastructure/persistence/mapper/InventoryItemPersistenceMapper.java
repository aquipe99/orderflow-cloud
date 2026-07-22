package com.orderflow.inventory_service.infrastructure.persistence.mapper;

import com.orderflow.inventory_service.domain.model.InventoryItem;
import com.orderflow.inventory_service.infrastructure.persistence.entity.InventoryItemEntity;
import org.springframework.stereotype.Component;

@Component
public class InventoryItemPersistenceMapper {
    public InventoryItemEntity toEntity(
            InventoryItem inventoryItem){

        return new InventoryItemEntity(
                inventoryItem.getId(),
                inventoryItem.getProductCode(),
                inventoryItem.getDescription(),
                inventoryItem.getAvailableStock(),
                inventoryItem.getReservedStock(),
                inventoryItem.getCreatedAt()
        );
    }

    public InventoryItem toDomain(
            InventoryItemEntity entity){

        return new InventoryItem(
                entity.getId(),
                entity.getProductCode(),
                entity.getDescription(),
                entity.getAvailableStock(),
                entity.getReservedStock(),
                entity.getCreatedAt()
        );

    }
}
