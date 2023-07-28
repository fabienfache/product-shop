package com.atlen.productshop.service;

import com.atlen.productshop.ProductShopApplication;
import com.atlen.productshop.entity.Product;
import com.atlen.productshop.exception.AlreadyExistException;
import com.atlen.productshop.exception.ConflictException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;


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
        Mockito.when(mockProductRepository.findById(any())).thenReturn(Optional.of(Product.builder().id(1L).build()));
        ProductDto product1 = productService.getProductById(1L);
        Mockito.verify(mockProductRepository,times(1)).findById(any());
        Assertions.assertEquals(1L,product1.getId());
    }

    @Test
    public void shouldThrowExceptionWhenGetProduct() throws NotFoundException {
        Mockito.when(mockProductRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class, () -> {
            ProductDto product1 = productService.getProductById(31L);  //Code under test
        });
        Mockito.verify(mockProductRepository,times(1)).findById(any());
        Assertions.assertEquals("Produit non trouvé", thrown.getMessage());
    }


    @Test
    public void shouldFindByCode() {
        Mockito.when(mockProductRepository.findByCode(any())).thenReturn(Product.builder().code("12").build());
        ProductDto productDto = productService.findByCode("12");
        Mockito.verify(mockProductRepository,times(1)).findByCode(any());
        Assertions.assertEquals(productDto.getCode(),"12");
    }

    @Test
    public void shouldCreateProduct() {
        Mockito.when(mockProductRepository.findByCode(any())).thenReturn(null);
        Mockito.when(mockProductRepository.save(any())).thenReturn(Product.builder().code("12").id(1L).build());
        ProductDto productDto = productService.createProduct(ProductDto.builder().code("12").build());
        Mockito.verify(mockProductRepository,times(1)).findByCode(any());
        Mockito.verify(mockProductRepository,times(1)).save(any());
        Assertions.assertEquals(productDto.getCode(),"12");
    }

    @Test
    public void shouldUpdateWhenCreateProduct() {
        Mockito.when(mockProductRepository.findById(any())).thenReturn(Optional.of(Product.builder().code("12").id(1L).build()));
        Mockito.when(mockProductRepository.save(any())).thenReturn(Product.builder().code("12").id(1L).build());
        ProductDto productDto = productService.createProduct(ProductDto.builder().code("12").id(1L).build());
        Mockito.verify(mockProductRepository,times(0)).findByCode(any());
        Mockito.verify(mockProductRepository,times(1)).findById(any());
        Mockito.verify(mockProductRepository,times(1)).save(any());
        Assertions.assertEquals(productDto.getCode(),"12");
    }

    @Test
    public void shouldThrowAlreadyExistExceptionWhenCreateProduct() {

        Mockito.when(mockProductRepository.findByCode(any())).thenReturn(Product.builder().code("12").build());
        AlreadyExistException thrown = Assertions.assertThrows(AlreadyExistException.class, () -> {
            ProductDto productDto = productService.createProduct(ProductDto.builder().code("12").build());
        });
        Assertions.assertEquals("Le code de ce produit existe déjà", thrown.getMessage());
        Mockito.verify(mockProductRepository,times(1)).findByCode(any());
        Mockito.verify(mockProductRepository,times(0)).save(any());
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenUpdateProduct() throws NotFoundException {
        Mockito.when(mockProductRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class, () -> {
            ProductDto product1 = productService.updateProduct(ProductDto.builder().id(31L).code("12").build(),31L);  //Code under test
        });
        Mockito.verify(mockProductRepository,times(1)).findById(any());
        Assertions.assertEquals("Produit non trouvé", thrown.getMessage());
    }
    @Test
    public void shouldThrowConflictExceptionWhenUpdateProduct() throws NotFoundException {
        Mockito.when(mockProductRepository.findById(any())).thenReturn(Optional.of(Product.builder().code("12").id(1L).build()));
        ConflictException thrown = Assertions.assertThrows(ConflictException.class, () -> {
            ProductDto product1 = productService.updateProduct(ProductDto.builder().id(31L).code("12").build(),31L);  //Code under test
        });
        Mockito.verify(mockProductRepository,times(1)).findById(any());
        Assertions.assertEquals("L'identifiant du produit n'est pas le même que l'identifiant passé en paramètre", thrown.getMessage());
    }

    @Test
    public void shouldSuccessWhenGetAllProduct() throws NotFoundException {
        Mockito.when(mockProductRepository.findAll()).thenReturn(List.of(Product.builder().id(1L).build(),Product.builder().id(2L).build()));
        List<ProductDto> products = productService.getAllProducts();
        Mockito.verify(mockProductRepository,times(1)).findAll();
        Assertions.assertEquals(2,products.size());
    }


    @Test
    public void shouldDeleteSuccessOneProduct() throws NotFoundException {
        doNothing().when(mockProductRepository).deleteById(any());
        productService.deleteProduct(1L);
        Mockito.verify(mockProductRepository,times(1)).deleteById(any());
    }


}