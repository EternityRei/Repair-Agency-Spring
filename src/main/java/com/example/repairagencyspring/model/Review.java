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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateWorkDone() {
        return dateWorkDone;
    }

    public void setDateWorkDone(Date dateWorkDone) {
        this.dateWorkDone = dateWorkDone;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
