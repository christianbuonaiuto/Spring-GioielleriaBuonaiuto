package com.gioielleriabuonaiuto.controller;

import com.gioielleriabuonaiuto.entity.Order;
import com.gioielleriabuonaiuto.entity.ProductInOrder;
import com.gioielleriabuonaiuto.service.OrderService;
import com.gioielleriabuonaiuto.service.ProductInOrderService;
import com.gioielleriabuonaiuto.support.ResponseMessage;
import com.gioielleriabuonaiuto.support.exception.OrderNotFoundException;
import com.gioielleriabuonaiuto.support.exception.ProductInOrderNotFoundException;
import com.gioielleriabuonaiuto.support.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/productInOrder")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductInOrderController {

    @Autowired
    private ProductInOrderService productInOrderService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public List<ProductInOrder> getAllProductInOrder(){ return productInOrderService.showAllProductInOrder(); }

    @GetMapping("/listByUser")
    public ResponseEntity getAllProductInOrderByEmail(@RequestParam(name = "emailUser") String emailUser){
        List<Order> lo;
        List<ProductInOrder> lpTotal = new ArrayList<ProductInOrder>();
        try{
            lo = orderService.showAllOrdersByUser(emailUser);
            for(Order order:lo){

                List<ProductInOrder> lp;
                try {
                    lp = productInOrderService.showAllProductInOrderByOrder(order.getIdo());
                } catch (OrderNotFoundException e) {
                        return new ResponseEntity<>(new ResponseMessage("Impossibile aggiungere productInOrder, ordine inesistente"), HttpStatus.BAD_REQUEST);
                }
                for(ProductInOrder p: lp){
                    lpTotal.add(p);
                }
            }
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new ResponseMessage("Impossibile aggiungere productInOrder, utente insesistente"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(lpTotal, HttpStatus.OK);
    }

    @GetMapping("/listByOrder")
    public ResponseEntity getAllProductInOrderByOrder(@RequestParam(name = "orderIdo") String orderIdo){
        List<ProductInOrder> p;
        try{
            p = productInOrderService.showAllProductInOrderByOrder(orderIdo);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(new ResponseMessage("Impossibile aggiungere productInOrder, ordine inesistente"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity createProductInOrder(@RequestBody @Valid ProductInOrder productInOrder){
        try{
            productInOrderService.addProductInOrder(productInOrder);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(new ResponseMessage("Impossibile aggiungere productInOrder, ordine inesistente."),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage("ProductInOrder aggiunto correttamente!"),HttpStatus.OK);
    }

}
