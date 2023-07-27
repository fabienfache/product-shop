package com.atlen.productshop.service;


import com.atlen.productshop.exception.NotFoundException;
import com.atlen.productshop.model.Product;
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
    public Product getProductById(Long id) throws NotFoundException {
        try {
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
        else return updateProduct(p);
    }

    public  Product updateProduct(Product p) throws Exception {

        // getProductById(p.getId()); pour vérifier la cohérence du code ou allons nous l"autoriser ?
        if(p.getId() == null){
            return productRepository.save(p);
        }
        else return createProduct(p);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    public void deleteProduct(Long id) throws NotFoundException {
        productRepository.deleteById(id);
    }
}
