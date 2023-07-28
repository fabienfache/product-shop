package com.atlen.productshop.service;

import com.atlen.productshop.ProductShopApplication;
import com.atlen.productshop.entity.Product;
import com.atlen.productshop.exception.NotFoundException;
import com.atlen.productshop.model.ProductDto;
import com.atlen.productshop.repository.ProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest(classes = ProductShopApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ProductServiceIntegrationTest {

    @Mock
    ProductRepository mockProductRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    public void shouldSuccessWhenGetProduct() throws NotFoundException {
        Product product = new Product();
        product.setId(1L);
        Mockito.when(mockProductRepository.findById(any())).thenReturn(Optional.of(product));
        ProductDto product1 = productService.getProductById(1L);
        Assertions.assertEquals(1L,product1.getId());
    }

    @Test
    public void shouldThrowExceptionWhenGetProduct() throws NotFoundException {
        Mockito.when(mockProductRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class, () -> {
            ProductDto product1 = productService.getProductById(31L);  //Code under test
        });

        Assertions.assertEquals("Produit non trouvé", thrown.getMessage());
    }

}