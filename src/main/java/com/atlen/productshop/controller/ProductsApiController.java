package com.atlen.productshop.controller;


import com.atlen.productshop.model.Product;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductsApiController implements ProductsApi {

    private static final Logger log = LoggerFactory.getLogger(ProductsApiController.class);


    public ResponseEntity<Product> addProduct(@Parameter(in = ParameterIn.DEFAULT, description = "Créé un nouveau produit", required=true, schema=@Schema()) @Valid @RequestBody Product body) {

        return new ResponseEntity<Product>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteProduct(@Parameter(in = ParameterIn.PATH, description = "identifiant du produit à supprimer", required=true, schema=@Schema()) @PathVariable("id") Long id) {

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Product> getProductById(@Parameter(in = ParameterIn.PATH, description = "Identifiant du produit", required=true, schema=@Schema()) @PathVariable("id") Long id) {


        return new ResponseEntity<Product>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Product>> getProducts() {


        return new ResponseEntity<List<Product>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Product> updateProduct(@Parameter(in = ParameterIn.PATH, description = "Identifiant du produit", required=true, schema=@Schema()) @PathVariable("id") Long id,@Parameter(in = ParameterIn.DEFAULT, description = "Produit à mettre à jour", schema=@Schema()) @Valid @RequestBody Product body) {


        return new ResponseEntity<Product>(HttpStatus.NOT_IMPLEMENTED);
    }

}
