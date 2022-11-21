package com.example.repairagencyspring.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderByUserDTO {
    private long id;
    private String title;
    private String description;
    private float cost;
    private String paymentStatus;
    private String workStatus;
    private float rating;
    private String contextRating;
}
