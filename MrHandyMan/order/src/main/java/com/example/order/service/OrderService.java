package com.example.order.service;

import com.example.Inventory.dto.InventoryDTO;
import com.example.Inventory.model.Inventory;
import com.example.order.common.ErrorOrderResponse;
import com.example.order.common.OrderResponse;
import com.example.order.common.SuccessOrderResponse;
import com.example.order.model.Orders;
import com.example.order.orderdto.OrderDTO;
import com.example.order.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
@Service
public class OrderService {

    private final WebClient webClient;
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient.Builder webClientBuilder,  OrderRepo orderRepo, ModelMapper modelMapper) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:9084/api/v1").build();
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }


    public List<OrderDTO> getAllOrders() {
        List<Orders>orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderResponse saveOrder(OrderDTO orderDTO) {

        Integer itemId = orderDTO.getItemId();
        try {
            InventoryDTO inventoryResponse = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/item/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

            System.out.println("csdncjdsncl------------------------------");
            System.out.println(inventoryResponse.getId());
            System.out.println("csdncjdsncl------------------------------");
            assert inventoryResponse != null;
            if (inventoryResponse.getQuantity() > 0) {
                orderRepo.save(modelMapper.map(orderDTO, Orders.class));
                return new SuccessOrderResponse(orderDTO);

            } else {
                return new ErrorOrderResponse("Item not available, please try later");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
return null;

    }

    public OrderDTO updateOrder(OrderDTO OrderDTO) {
        orderRepo.save(modelMapper.map(OrderDTO, Orders.class));
        return OrderDTO;
    }

    public String deleteOrder(Integer orderId) {
        orderRepo.deleteById(orderId);
        return "Order deleted";
    }

    public OrderDTO getOrderById(Integer orderId) {
        Orders order = orderRepo.getOrderById(orderId);
        return modelMapper.map(order, OrderDTO.class);
    }
}
