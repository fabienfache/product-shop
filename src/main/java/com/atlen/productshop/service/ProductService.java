package com.atlen.productshop.service;


import com.atlen.productshop.entity.Product;
import com.atlen.productshop.exception.NotFoundException;
import com.atlen.productshop.model.ProductDto;
import com.atlen.productshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto getProductById(Long id) throws NotFoundException {
        try {
            return ProductDto.of(productRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new NotFoundException("Produit non trouvé");
        }
    }

    @Transactional(readOnly = true)
    public ProductDto findByCode(String code) {
        return ProductDto.of(productRepository.findByCode(code));
    }


    public ProductDto createProduct(ProductDto p) throws Exception {
        if(p.getId() == null) {
            ProductDto pb = findByCode(p.getCode());
            if (pb == null) {
                return ProductDto.of(productRepository.save(Product.of(p)));
            } else throw new Exception("Le code de ce produit existe déjà");
        }
        else return updateProduct(p,p.getId());
    }

    public ProductDto updateProduct(ProductDto p, Long id) throws Exception {

        ProductDto product = getProductById(id);
        if(product != null && product.getId() != p.getId()) {
            if (p.getId() != null) {
                return ProductDto.of(productRepository.save(Product.of(p)));
            } else return createProduct(p);
        }
        throw new NotFoundException("Produit non trouvé");
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts(){
        return productRepository.findAll().stream().map(x -> ProductDto.of(x)).toList();
    }


    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
