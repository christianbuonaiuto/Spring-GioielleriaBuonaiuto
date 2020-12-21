package com.gioielleriabuonaiuto.service;

import com.gioielleriabuonaiuto.entity.ProductInCart;
import com.gioielleriabuonaiuto.entity.User;
import com.gioielleriabuonaiuto.repository.ProductInCartRepository;
import com.gioielleriabuonaiuto.repository.UserRepository;
import com.gioielleriabuonaiuto.support.exception.ProductInCartNotFoundException;
import com.gioielleriabuonaiuto.support.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInCartService {

    @Autowired
    private ProductInCartRepository productInCartRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ProductInCart> showAllProductInCart() { return productInCartRepository.findAll(); }

    @Transactional(readOnly = true)
    public List<ProductInCart> showAllProductInCartByUser(String userEmail) throws UserNotFoundException {
        if(!userRepository.existsByEmail(userEmail)){
            throw new UserNotFoundException();
        }
        User u = userRepository.getByEmail(userEmail);
        return productInCartRepository.getAllByUsero(u);
    }

    @Transactional(readOnly = false)
    public ProductInCart addProductInCart(ProductInCart productInCart) throws UserNotFoundException{
        if(!userRepository.existsByEmail(productInCart.getUsero().getEmail())){
            throw new UserNotFoundException();
        }
        return productInCartRepository.save(productInCart);
    }

    @Transactional(readOnly = true)
    public ProductInCart showProductInCart(int ido) throws ProductInCartNotFoundException {
        if(!productInCartRepository.existsByIdo(ido)){
            throw new ProductInCartNotFoundException();
        }
        return productInCartRepository.getByIdo(ido);
    }

    @Transactional(readOnly = false)
    public void deleteProductInCart(int ido)throws ProductInCartNotFoundException {
        if(!productInCartRepository.existsByIdo(ido)){
            throw new ProductInCartNotFoundException();
        }
        productInCartRepository.deleteByIdo(ido);
    }

    @Transactional(readOnly = false)
    public void editQtyByIdo(int ido, int qty)throws ProductInCartNotFoundException{
        if(!productInCartRepository.existsByIdo(ido)){
            throw new ProductInCartNotFoundException();
        }
        ProductInCart p = productInCartRepository.getByIdo(ido);
        p.setQuantity(qty);
    }

    @Transactional(readOnly = false)
    public void removeAllProductInCartByUser(String userEmail) throws UserNotFoundException{
        if(!userRepository.existsByEmail(userEmail)){
            throw new UserNotFoundException();
        }
        productInCartRepository.deleteAllByUsero(userRepository.getByEmail(userEmail));
    }

}
