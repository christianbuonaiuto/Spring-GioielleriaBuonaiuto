package com.gioielleriabuonaiuto.controller;

import com.gioielleriabuonaiuto.entity.ProductInCart;
import com.gioielleriabuonaiuto.service.ProductInCartService;
import com.gioielleriabuonaiuto.support.ResponseMessage;
import com.gioielleriabuonaiuto.support.exception.ProductInCartNotFoundException;
import com.gioielleriabuonaiuto.support.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/productInCart")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductInCartController {

    @Autowired
    private ProductInCartService productInCartService;

    @GetMapping("/list")
    public List<ProductInCart> getAllProductInCart() { return productInCartService.showAllProductInCart(); }

    @GetMapping("/listByUser")
    public ResponseEntity getAllProductInCartByUser(@RequestParam(name = "emailUser") String emailUser){
        List<ProductInCart> p;
        try{
            p = productInCartService.showAllProductInCartByUser(emailUser);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage("Impossibile resistuire la lista dei prodotti nel carrelo, utente inesistente."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity createProductInCart(@RequestBody @Valid ProductInCart productInCart){
        ProductInCart p;
        try{
            p = productInCartService.addProductInCart(productInCart);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage("Utente non trovato, impossibile aggiungere il prodotto nel carrello."),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(p,HttpStatus.OK);
    }


    @DeleteMapping("/deleteOne/{ido}")
    public ResponseEntity deleteOne(@RequestBody @Valid @PathVariable("ido") int ido){
        try{
            productInCartService.deleteProductInCart(ido);
        } catch (ProductInCartNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage("Id inesistente. Impossibile eliminare l'elemento"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage("ProductInCart eliminato correttamente!"),HttpStatus.OK);
    }

    @PutMapping("/editQty")
    public ResponseEntity modifyQuantity(@RequestBody @Valid ProductInCart productInCart){
        try {
            productInCartService.editQtyByIdo(productInCart.getIdo(), productInCart.getQuantity());
        } catch (ProductInCartNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage("Id inesistente. Impossibile modificare la quantità"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage("Quantità modificata correttamente!"),HttpStatus.OK);
    }

    @DeleteMapping("/deleteListByUser")
    public ResponseEntity deleteAllByUserEmail(@RequestParam(name = "emailUser") String emailUser){
        try{
            productInCartService.removeAllProductInCartByUser(emailUser);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage("Impossibile eliminare ProductInCart, utente inesistente."),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage("ProductInCart eliminato correttamente!"),HttpStatus.OK);
    }


}
