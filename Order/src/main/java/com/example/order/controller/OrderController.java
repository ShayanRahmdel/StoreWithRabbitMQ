package com.example.order.controller;


import com.example.order.model.dto.OrderRequest;
import com.example.order.model.dto.OrderResponse;
import com.example.order.model.dto.ProductResponse;
import com.example.order.service.OrderService;
import com.example.order.service.ProductResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductResponseService productResponseService;

    @PostMapping("/create")
    public ResponseEntity<List<OrderResponse>> createOrder(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.create(orderRequest), HttpStatus.CREATED);
    }


    @GetMapping("/submit")
    public ResponseEntity<String> submitOrder(@RequestParam Integer id) {
        orderService.submitOrder(id);
        return new ResponseEntity<>("submit", HttpStatus.OK);
    }

    @GetMapping("/load-by-id")
    public ResponseEntity<OrderResponse> loadById(@RequestParam Integer orderId) {
        return new ResponseEntity<>(orderService.findById(orderId), HttpStatus.OK);
    }

    @DeleteMapping("/delete-order")
    public void deleteOrder(@RequestParam Integer orderId) {
        orderService.deleteOrder(orderId);
        System.out.println("success deleted!");
    }

    @GetMapping("/see-products")
    public ResponseEntity<List<ProductResponse>> seeProducts() {
        return new ResponseEntity<>(productResponseService.getProducts(), HttpStatus.OK);
    }

}
