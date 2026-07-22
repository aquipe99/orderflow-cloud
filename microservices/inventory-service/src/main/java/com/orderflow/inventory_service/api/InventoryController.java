package com.orderflow.inventory_service.api;

import com.orderflow.inventory_service.application.port.input.ReserveInventoryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final ReserveInventoryUseCase reserveInventoryUseCase;


    public InventoryController(
            ReserveInventoryUseCase reserveInventoryUseCase) {

        this.reserveInventoryUseCase = reserveInventoryUseCase;
    }



//    @PutMapping("/{productCode}/reserve")
//    public ResponseEntity<Void> reserve(
//            @PathVariable String productCode,
//            @RequestParam Integer quantity) {
//
//
//        reserveInventoryUseCase.reserve(
//                productCode,
//                quantity
//        );
//
//
//        return ResponseEntity.ok().build();
//    }
}
