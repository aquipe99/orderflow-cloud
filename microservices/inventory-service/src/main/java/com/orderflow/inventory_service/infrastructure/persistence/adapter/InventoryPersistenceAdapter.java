package com.orderflow.inventory_service.infrastructure.persistence.adapter;

import com.orderflow.inventory_service.application.port.output.FindInventoryItemPort;
import com.orderflow.inventory_service.application.port.output.SaveInventoryItemPort;
import com.orderflow.inventory_service.domain.model.InventoryItem;
import com.orderflow.inventory_service.infrastructure.persistence.mapper.InventoryItemPersistenceMapper;
import com.orderflow.inventory_service.infrastructure.persistence.repository.InventoryItemJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InventoryPersistenceAdapter implements FindInventoryItemPort,
        SaveInventoryItemPort {

    private final InventoryItemJpaRepository repository;
    private final InventoryItemPersistenceMapper mapper;

    public InventoryPersistenceAdapter(InventoryItemJpaRepository repository, InventoryItemPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Optional<InventoryItem> findByProductCode(String productCode) {

        return repository.findByProductCode(productCode)
                .map(mapper::toDomain);
    }

    @Override
    public InventoryItem save(InventoryItem inventoryItem) {
        repository.save(
                mapper.toEntity(inventoryItem)
        );


        return inventoryItem;
    }
}
