package com.example.thepirates.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select distinct p from Product p join fetch p.productOptions where p.id = :id")
    Optional<Product> findProductWithOptionById(@Param("id") Long id);

    @Query("select distinct p from Product p join fetch p.productOptions order by p.createdDate desc")
    List<Product> findAllWithOption();
}
