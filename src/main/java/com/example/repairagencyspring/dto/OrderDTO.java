package com.example.repairagencyspring.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Comparator;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private long id;
    private String title;
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
}
