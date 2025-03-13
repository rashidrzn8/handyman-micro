package com.example.Inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    private Integer id;
    private Integer itemId;
    private Integer productId;
    private Integer quantity;

    // Getters and Setters
}

