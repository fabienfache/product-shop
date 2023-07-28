package com.atlen.productshop.service;


import com.atlen.productshop.entity.Product;
import com.atlen.productshop.exception.AlreadyExistException;
import com.atlen.productshop.exception.ConflictException;
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
        return  ProductDto.of(productRepository.findByCode(code));
    }


    public ProductDto createProduct(ProductDto p)  throws NotFoundException, AlreadyExistException {
        if(p.getId() == null) {
            ProductDto pb = findByCode(p.getCode());
            if (pb == null) {
                return ProductDto.of(productRepository.save(Product.of(p)));
            } else throw new AlreadyExistException("Le code de ce produit existe déjà");
        }
        else return updateProduct(p,p.getId());
    }

    public ProductDto updateProduct(ProductDto p, Long id) throws NotFoundException, AlreadyExistException {

        ProductDto product = getProductById(id);
        if(product != null) {
                if(product.getId()== p.getId()) {
                    return ProductDto.of(productRepository.save(Product.of(p)));
                }
                else
                    throw new ConflictException("L'identifiant du produit n'est pas le même que l'identifiant passé en paramètre");
        }
        throw new NotFoundException("Produit non trouvé");
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts(){
        return productRepository.findAll().stream().map(ProductDto::of).toList();
    }


    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
