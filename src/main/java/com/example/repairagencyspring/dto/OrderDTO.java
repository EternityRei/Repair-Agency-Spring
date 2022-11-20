package com.example.repairagencyspring.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Comparator;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private long id;
    @NotNull
    private String title;
    @NotNull
    @Size(min = 10, message = "order description must be at least 10 characters")
    private String description;
    private float cost;
    private String paymentStatus;
    private String workStatus;
    private int userId;
    private int workerId;

    private Long currentTime = System.currentTimeMillis();
    private Timestamp timestamp = new Timestamp(currentTime);


    public static class IdComparator implements Comparator<OrderDTO> {
        @Override
        public int compare(OrderDTO o1, OrderDTO o2) {
            return String.valueOf(o1.getId()).compareTo(String.valueOf(o2.getId()));
        }
    }

    public static class DateComparator implements Comparator<OrderDTO>{
        @Override
        public int compare(OrderDTO o1, OrderDTO o2) {
            return String.valueOf(o1.getTimestamp()).compareTo(String.valueOf(o2.getTimestamp()));
        }
    }

    public static class WorkStatusComparator implements Comparator<OrderDTO>{
        @Override
        public int compare(OrderDTO o1, OrderDTO o2) {
            return o1.getWorkStatus().compareTo(o2.getWorkStatus());
        }
    }

    public static class PaymentStatusComparator implements Comparator<OrderDTO>{
        @Override
        public int compare(OrderDTO o1, OrderDTO o2) {
            return o1.getPaymentStatus().compareTo(o2.getPaymentStatus());
        }
    }

    public static class PriceComparator implements Comparator<OrderDTO>{
        @Override
        public int compare(OrderDTO o1, OrderDTO o2) {
            return String.valueOf(o1.getCost()).compareTo(String.valueOf(o2.getCost()));
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
