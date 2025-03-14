package com.example.order.common;

import com.example.order.orderdto.OrderDTO;
import lombok.Getter;

@Getter
public class ErrorOrderResponse implements OrderResponse {
    private final String errorMessage;

    public ErrorOrderResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
