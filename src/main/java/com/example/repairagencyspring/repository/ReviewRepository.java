package com.example.repairagencyspring.repository;

import com.example.repairagencyspring.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findByUserIdAndOrderId(long userId, long orderId);
    public List<Review> findAllByUserId(long userId);

    @Query(value = "select * from review", nativeQuery = true)
    public List<Review> getAll();
}
