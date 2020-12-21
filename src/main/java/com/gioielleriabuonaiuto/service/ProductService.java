package com.gioielleriabuonaiuto.service;

import com.gioielleriabuonaiuto.entity.Product;
import com.gioielleriabuonaiuto.repository.ProductRepository;
import com.gioielleriabuonaiuto.support.exception.ProductAlreadyExistsException;
import com.gioielleriabuonaiuto.support.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> showAllProducts() { return productRepository.findAll(); }

    @Transactional(readOnly = true)
    public List<Product> showAllProductsAvailabe() { return productRepository.findAllAvailable(); }

    @Transactional(readOnly = true)
    public List<Product> showAllProductsOrderByPriceIncr() { return productRepository.findAllOrderByPriceIncr(); }

    @Transactional(readOnly = true)
    public List<Product> showAllProductsOrderByPriceDecr() { return productRepository.findAllOrderByPriceDecr(); }

    @Transactional(readOnly = true)
    public List<String> showNameAllBrand() {
        return productRepository.getNameAllBrand();
    }

    @Transactional(readOnly = true)
    public List<String> showAllCategories(){
        return productRepository.getAllCategories();
    }

    @Transactional(readOnly = false)
    public void addProduct(Product product) throws ProductAlreadyExistsException {
        if(productRepository.existsByCode(product.getCode())){
            throw new ProductAlreadyExistsException();
        }
        productRepository.save(product);
    }
    @Transactional(readOnly = true)
    public Product showProduct(String productCode) throws ProductNotFoundException{
        if(!productRepository.existsByCode(productCode)){
            throw new ProductNotFoundException();
        }
        return productRepository.getByCode(productCode);
    }


    @Transactional(readOnly = false)
    public void editDescriptionQuantityPrice(String code, String description, int qnt, double price) throws ProductNotFoundException{
        if(!productRepository.existsByCode(code) || code == null){
            throw new ProductNotFoundException();
        }
        Product p = productRepository.getByCode(code);
        p.setDescription(description);
        p.setQuantity(qnt);
        p.setPrice(price);
        p.setLastModify(new Date());
    }

    @Transactional(readOnly = false)
    public void editQuantity(String code, int qnt) throws ProductNotFoundException{
        if(!productRepository.existsByCode(code) || code == null){
            throw new ProductNotFoundException();
        }
        Product p = productRepository.getByCode(code);
        p.setQuantity(qnt);
        p.setLastModify(new Date());
    }

    @Transactional(readOnly = true)
    public List<Product> showAllByCategory(String category){ return productRepository.findAllByCategory(category);  }

    @Transactional(readOnly = true)
    public List<Product> showAllByBrand(String brand){ return productRepository.findAllByBrand(brand); }
}
