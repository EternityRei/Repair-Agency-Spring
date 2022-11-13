package com.example.repairagencyspring.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@Component
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "content")
    private String content;
    @Column(name = "date_work_done")
    private Date dateWorkDone;
    @Column(name = "rating")
    private float rating;
    @Column(name = "userId")
    private int userId;
    @Column(name = "orderId")
    private int orderId;
}
