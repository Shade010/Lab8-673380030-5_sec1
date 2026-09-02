package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reviewer;

    private Integer rating; // 1-5
    private String comment;
    private LocalDate reviewDate;

    // FK อยู่ฝั่ง Many เสมอ
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Review() {}

    public Review(String reviewer, Integer rating, String comment, LocalDate reviewDate) {
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReviewer() { return reviewer; }
    public void setReviewer(String reviewer) { this.reviewer = reviewer; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDate getReviewDate() { return reviewDate; }
    public void setReviewDate(LocalDate reviewDate) { this.reviewDate = reviewDate; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}