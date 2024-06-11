package com.example.order.repostory;

import com.example.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("select o from Order o where o.userId = :userId and o.isSend=false ")
    List<Order> findOrderByUserId(@Param("userId") Integer userId);
}
