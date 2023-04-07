package com.example.microservicepartb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservicepartb.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
