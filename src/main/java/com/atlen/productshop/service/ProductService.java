package com.atlen.productshop.service;


import com.atlen.productshop.exception.NotFoundException;
import com.atlen.productshop.model.Product;
import com.atlen.productshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Product getProductById(Long id) throws NotFoundException {
        try {
            Optional<Product> prod = productRepository.findById(id);
            return productRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new NotFoundException("Produit non trouvé");
        }
    }

    @Transactional(readOnly = true)
    public Product findByCode(String code) {
        return productRepository.findByCode(code);
    }


    public  Product createProduct(Product p) throws Exception {
        if(p.getId() == null) {
            Product pb = findByCode(p.getCode());
            if (pb == null) {
                return productRepository.save(p);
            } else throw new Exception("Le code de ce produit existe déjà");
        }
        else return updateProduct(p,p.getId());
    }

    public  Product updateProduct(Product p,Long id) throws Exception {

        Product product = getProductById(id);
        if(product != null && product.getId() != p.getId()) {
            if (p.getId() != null) {
                return productRepository.save(p);
            } else return createProduct(p);
        }
        throw new NotFoundException("Produit non trouvé");
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
