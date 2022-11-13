package com.example.repairagencyspring.repository;

import com.example.repairagencyspring.dto.OrderDTO;
import com.example.repairagencyspring.model.Order;
import com.example.repairagencyspring.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Review> findAllReviewsById(long id);
    List<Order> findAllOrdersByUserId(long userId);
    List<Order> findOrderByUserId(long userId);
    Order getByUserId(int userId);

    List<OrderDTO> findAllOrdersByTimestampIs(Timestamp timestamp);
    List<OrderDTO> findAllOrdersByWorkStatusIs(String workStatus);
    List<OrderDTO> findAllOrdersByPaymentStatusIs(String paymentStatus);

    List<OrderDTO> findAllOrdersByCostIs(float cost);

    List<OrderDTO> findAllOrdersById(long id);

    Order findOrderByUserIdAndId(long userId, long id);

    @Query(value = "select * from orders",
            nativeQuery = true)
    List<Order> getAll();
}
