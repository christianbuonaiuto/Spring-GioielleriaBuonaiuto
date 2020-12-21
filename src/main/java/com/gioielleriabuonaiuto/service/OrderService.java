package com.gioielleriabuonaiuto.service;

import com.gioielleriabuonaiuto.entity.Order;
import com.gioielleriabuonaiuto.entity.User;
import com.gioielleriabuonaiuto.repository.OrderRepository;
import com.gioielleriabuonaiuto.repository.UserRepository;
import com.gioielleriabuonaiuto.support.exception.OrderNotFoundException;
import com.gioielleriabuonaiuto.support.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Order> showAllOrders() { return orderRepository.findAll(); }

    @Transactional(readOnly = true)
    public List<Order> showAllOrdersByUser(String emailUser) throws UserNotFoundException{
        if(!userRepository.existsByEmail(emailUser)){
            throw new UserNotFoundException();
        }
        User u = userRepository.getByEmail(emailUser);
        return orderRepository.getAllByUsero(u);
    }

    @Transactional(readOnly = false)
    public Order addOrder(Order order) throws UserNotFoundException{
       if(!userRepository.existsByEmail((order.getUsero().getEmail()))){
            throw new UserNotFoundException();
        }
        Order o=orderRepository.save(order);

       return o;
    }

    @Transactional(readOnly = true)
    public Order showOrder(String orderIdo) throws OrderNotFoundException {
        if(!orderRepository.existsByIdo(orderIdo)){
            throw new OrderNotFoundException();
        }
        return orderRepository.getByIdo(orderIdo);
    }


}
