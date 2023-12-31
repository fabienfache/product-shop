package com.atlen.productshop.repository;

import com.atlen.productshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    Product findByCode(String code);
}
