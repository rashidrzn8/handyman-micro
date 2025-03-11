package com.example.order.orderdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private int id;
    private int itemId;
    private String orderDate;
    private int amount;
}
