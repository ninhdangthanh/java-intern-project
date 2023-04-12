package com.study.backend.service;

import com.study.backend.entity.Product;
import com.study.backend.entity.User;
import com.study.backend.repository.ProductRepository;
import com.study.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public Product createProduct(Product product, Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new EntityNotFoundException("User with id " + user_id + " not found"));;
        product.setUser(user);
        return productRepository.save(product);
    }

    public void updateProduct(Product product) {
        // Perform any necessary validation
        productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByUserId(Long userId) {
        return productRepository.findByUserId(userId);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
