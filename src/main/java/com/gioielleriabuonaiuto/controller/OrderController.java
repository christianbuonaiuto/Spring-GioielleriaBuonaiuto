package com.gioielleriabuonaiuto.controller;

import com.gioielleriabuonaiuto.entity.Order;
import com.gioielleriabuonaiuto.service.OrderService;
import com.gioielleriabuonaiuto.support.ResponseMessage;
import com.gioielleriabuonaiuto.support.exception.OrderNotFoundException;
import com.gioielleriabuonaiuto.support.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public List<Order> getAllOrders(){
        return orderService.showAllOrders();
    }

    @PostMapping("/add")
    public ResponseEntity createOrder(@RequestBody @Valid Order order){
        order.setDatePurchase(new Date());
        Order o;
        try{
            o=orderService.addOrder(order);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new ResponseMessage("Impossibile aggiungere l'ordine. L'utente associato non esiste."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @GetMapping("/listByUser")
    public ResponseEntity getAllOrdersByUser(@RequestParam(name = "emailUser") String emailUser){
        List<Order> l = null;
        try{
            l = orderService.showAllOrdersByUser(emailUser);
        } catch (UserNotFoundException e) {
            new ResponseEntity<>(new ResponseMessage("Impossibile cercare l'ordine di un utente inesistente."),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(l,HttpStatus.OK);
    }

    @GetMapping("/getOne")
    public ResponseEntity getOne(@RequestBody @Valid String idOrder){
        Order o;
        try{
            o=orderService.showOrder(idOrder);
        } catch (OrderNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage("Ordine non trovato"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(o,HttpStatus.OK);
    }



}
