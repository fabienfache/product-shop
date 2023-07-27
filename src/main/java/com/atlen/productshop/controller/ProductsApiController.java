package com.atlen.productshop.controller;


import com.atlen.productshop.exception.NotFoundException;
import com.atlen.productshop.model.Product;
import com.atlen.productshop.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductsApiController implements ProductsApi {

    private static final Logger log = LoggerFactory.getLogger(ProductsApiController.class);

    @Autowired
    public ProductService productService;

    public ResponseEntity<Product> addProduct(@Parameter(in = ParameterIn.DEFAULT, description = "Créé un nouveau produit", required=true, schema=@Schema()) @Valid @RequestBody Product product) throws Exception {
        Product product1 = productService.createProduct(product);
        return ResponseEntity.ok(product1);
    }

    public ResponseEntity<Void> deleteProduct(@Parameter(in = ParameterIn.PATH, description = "identifiant du produit à supprimer", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Product> getProductById(@Parameter(in = ParameterIn.PATH, description = "Identifiant du produit", required=true, schema=@Schema()) @PathVariable("id") Long id) throws NotFoundException {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<Product> updateProduct(@Parameter(in = ParameterIn.DEFAULT, description = "Produit à mettre à jour", schema=@Schema()) @Valid @RequestBody Product product) throws Exception {
        Product product1 = productService.updateProduct(product);
        return ResponseEntity.ok(product1);
    }

}
