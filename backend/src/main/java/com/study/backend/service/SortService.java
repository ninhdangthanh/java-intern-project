package com.study.backend.service;

import com.study.backend.entity.Product;
import com.study.backend.entity.Sort;
import com.study.backend.entity.User;
import com.study.backend.repository.ProductRepository;
import com.study.backend.repository.SortRepository;
import com.study.backend.repository.UserRepository;
import com.study.backend.request.SortRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class SortService {

    @Autowired
    private SortRepository sortRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    public Sort createSort(SortRaw sortRaw) {
        User user = userRepository.findById(sortRaw.getUser_id()).orElse(null);
        Product product = productRepository.findById(sortRaw.getProduct_id()).orElse(null);

        Sort sort = new Sort();
        sort.setQuantity(sortRaw.getQuantity());
        sort.setUser(user);
        sort.setProduct(product);

        return sortRepository.save(sort);
    }

    public void updateSort(@Valid Sort sort) {
        // Perform any necessary validation
        sortRepository.save(sort);
    }


    public Sort getSortById(Long id) {
        return sortRepository.findById(id).orElse(null);
    }

    public void deleteSortById(Long id) {
        sortRepository.deleteById(id);
    }

    public List<Sort> getSortsByUserId(Long id) {
        return sortRepository.findByUserId(id);
    }
}