package com.atlen.productshop.service;

import com.atlen.productshop.exception.NotFoundException;
import com.atlen.productshop.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;



@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    public void shouldGetTheNumberOfTheProductRecorded(){
        List<Product> listProduct = productService.getAllProducts();
        Assertions.assertEquals(listProduct.size(),30);
    }

    @Test
    public void shouldGetTheCodeProductById1000() throws NotFoundException {
        Product product = productService.getProductById(1000L);
        Assertions.assertEquals(product.getCode(),"f230fh0g3");
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenGetTheProductById31() throws NotFoundException {

        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class, () -> {
            Product product = productService.getProductById(31L);  //Code under test
        });

        Assertions.assertEquals("Produit non trouvé", thrown.getMessage());
    }

}
