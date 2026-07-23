package com.orderflow.inventory_service.infrastructure.persistence.adapter;

import com.orderflow.inventory_service.application.port.output.ExistsProcessedEventPort;
import com.orderflow.inventory_service.application.port.output.SaveProcessedEventPort;
import com.orderflow.inventory_service.infrastructure.persistence.entity.ProcessedEventEntity;
import com.orderflow.inventory_service.infrastructure.persistence.repository.ProcessedEventRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class ProcessedEventPersistenceAdapter  implements ExistsProcessedEventPort, SaveProcessedEventPort {

//    private static final String CONSUMER = "inventory-service";
    private final ProcessedEventRepository repository;

    public ProcessedEventPersistenceAdapter(ProcessedEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean exists(UUID eventId) {
        return repository.existsById(eventId);
    }

    @Override
    public void save(UUID eventId, String consumer) {
        ProcessedEventEntity entity= new ProcessedEventEntity(
                eventId,
                consumer,
                Instant.now()
        );
        repository.save(entity);
    }
}
