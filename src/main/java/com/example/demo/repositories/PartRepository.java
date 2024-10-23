package com.example.demo.repositories;

import com.example.demo.domain.Part;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PartRepository extends CrudRepository<Part, Long> {

    // Custom search query using a keyword
    @Query("SELECT p FROM Part p WHERE p.name LIKE %?1%")
    List<Part> search(String keyword);

    // Method to find a part by its exact name
    Optional<Part> findByName(String name);  // This method will resolve the 'findByName' issue
}

