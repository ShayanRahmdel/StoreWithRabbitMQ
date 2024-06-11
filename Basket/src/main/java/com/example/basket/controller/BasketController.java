package com.example.basket.controller;


import com.example.basket.model.dto.BasketResponse;
import com.example.basket.service.BasketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/basket")
public class BasketController {

    BasketService basketService;


    @GetMapping("/create")
    public ResponseEntity<BasketResponse> create() throws JsonProcessingException {
        return new ResponseEntity<>(basketService.createOrUpdate(), HttpStatus.OK);
    }

    @GetMapping("/load-by-id")
    public ResponseEntity<BasketResponse> loadById(@RequestParam Integer id){
        return new ResponseEntity<>(basketService.loadById(id),HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestParam Integer id){
        basketService.delete(id);
        return new ResponseEntity<>("success delete",HttpStatus.OK);
    }

}
