package com.example.repairagencyspring.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@Component
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private float cost;
    @Column(name = "payment_status")
    private String paymentStatus;
    @Column(name = "work_status")
    private String workStatus;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "worker_id")
    private int workerId;
    @Column(name = "date_work_add")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getId() == order.getId()
                && Float.compare(order.getCost(), getCost()) == 0
                && getUserId() == order.getUserId()
                && getWorkerId() == order.getWorkerId()
                && getTitle().equals(order.getTitle())
                && getDescription().equals(order.getDescription())
                && getPaymentStatus().equals(order.getPaymentStatus())
                && getWorkStatus().equals(order.getWorkStatus())
                && getTimestamp().equals(order.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(),
                getCost(), getPaymentStatus(), getWorkStatus(),
                getUserId(), getWorkerId(), getTimestamp());
    }
}
