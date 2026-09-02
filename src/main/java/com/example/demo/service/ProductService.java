package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.Review;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.strategy.DiscountContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private DiscountContext discountContext;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        if (product.getDetail() != null) {
            product.getDetail().setProduct(product);
        }

        if (product.getReviews() != null) {
            product.getReviews().removeIf(r ->
                (r.getReviewer() == null || r.getReviewer().isBlank())
            );
            for (Review review : product.getReviews()) {
                review.setProduct(product);
                if (review.getReviewDate() == null) {
                    review.setReviewDate(LocalDate.now());
                }
            }
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Double getFinalPrice(Product product) {
        return discountContext.calculateFinalPrice(product.getPrice(), product.getDiscountType());
    }

    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public Review addReview(Long productId, Review review) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));
        review.setProduct(product);
        if (review.getReviewDate() == null) {
            review.setReviewDate(LocalDate.now());
        }
        return reviewRepository.save(review);
    }
}