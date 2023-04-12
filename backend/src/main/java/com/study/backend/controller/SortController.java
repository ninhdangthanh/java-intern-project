package com.study.backend.controller;

import com.study.backend.entity.Sort;
import com.study.backend.request.SortRaw;
import com.study.backend.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sorts")
public class SortController {

    @Autowired
    private SortService sortService;

    @PostMapping
    public ResponseEntity<Sort> createSort(@RequestBody SortRaw sortRaw) {
        Sort createdSort = sortService.createSort(sortRaw);
        return new ResponseEntity<>(createdSort, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sort> getSortById(@PathVariable Long id) {
        Sort sort = sortService.getSortById(id);
        if (sort == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sort, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Sort>> getSortByUserId(@PathVariable Long id) {
        List<Sort> sorts = sortService.getSortsByUserId(id);
        if (sorts == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sorts, HttpStatus.OK);
    }

    @PutMapping("/{id}/{quantity}")
    public ResponseEntity<Void> updateSort(@PathVariable Long id, @PathVariable int quantity) {
        Sort existingSort = sortService.getSortById(id);
        if (existingSort == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Perform any necessary validation
        existingSort.setQuantity(quantity);
        sortService.updateSort(existingSort);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSortById(@PathVariable Long id) {
        Sort existingSort = sortService.getSortById(id);
        if (existingSort == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sortService.deleteSortById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
