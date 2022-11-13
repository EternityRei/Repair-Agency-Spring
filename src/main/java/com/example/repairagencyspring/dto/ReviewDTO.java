package com.example.repairagencyspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private String content;
    private Date dateWorkDone;
    private float rating;
    private int userId;
    private int orderId;
}
