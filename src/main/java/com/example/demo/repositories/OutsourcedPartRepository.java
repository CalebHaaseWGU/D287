package com.example.demo.repositories;

import com.example.demo.domain.OutsourcedPart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OutsourcedPartRepository extends CrudRepository<OutsourcedPart, Long> {

    // Method to find an outsourced part by its exact name
    Optional<OutsourcedPart> findByName(String name);  // This method will resolve the 'findByName' issue
}

