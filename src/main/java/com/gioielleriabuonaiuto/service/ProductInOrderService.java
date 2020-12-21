package com.gioielleriabuonaiuto.service;

import com.gioielleriabuonaiuto.entity.Order;
import com.gioielleriabuonaiuto.entity.ProductInOrder;
import com.gioielleriabuonaiuto.repository.OrderRepository;
import com.gioielleriabuonaiuto.repository.ProductInOrderRepository;
import com.gioielleriabuonaiuto.support.exception.OrderNotFoundException;
import com.gioielleriabuonaiuto.support.exception.ProductInOrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInOrderService {

    @Autowired
    private ProductInOrderRepository productInOrderRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<ProductInOrder> showAllProductInOrder(){ return productInOrderRepository.findAll(); }

    @Transactional(readOnly = true)
    public List<ProductInOrder> showAllProductInOrderByOrder(String idOrder) throws OrderNotFoundException{
        if(!orderRepository.existsByIdo(idOrder)){
            throw new OrderNotFoundException();
        }
        Order o = orderRepository.getByIdo(idOrder);
        return productInOrderRepository.getAllByOrder(o);
    }

    @Transactional(readOnly = false)
    public void addProductInOrder(ProductInOrder p) throws OrderNotFoundException {
        if(!orderRepository.existsByIdo(p.getOrdero().getIdo())){
            throw new OrderNotFoundException();
        }
        productInOrderRepository.save(p);
    }

    @Transactional(readOnly = true)
    public ProductInOrder showProductInOrder(int id) throws ProductInOrderNotFoundException {
        if(!productInOrderRepository.existsByIdo(id)){
            throw new ProductInOrderNotFoundException();
        }
        return productInOrderRepository.getByIdo(id);
    }
}
