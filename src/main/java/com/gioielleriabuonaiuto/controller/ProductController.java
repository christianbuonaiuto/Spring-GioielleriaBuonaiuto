package com.gioielleriabuonaiuto.controller;

import com.gioielleriabuonaiuto.entity.Product;
import com.gioielleriabuonaiuto.service.ProductService;
import com.gioielleriabuonaiuto.support.ResponseMessage;
import com.gioielleriabuonaiuto.support.exception.ProductAlreadyExistsException;
import com.gioielleriabuonaiuto.support.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> getAllProducts(){
        return productService.showAllProducts();
    }

    @GetMapping("/listAvailable")
    public List<Product> getAllProductsAvailable(){
        return productService.showAllProductsAvailabe();
    }

    @GetMapping("/listOrderByPriceIncr")
    public List<Product> getAllProductsOrderByPriceIncr(){
        return productService.showAllProductsOrderByPriceIncr();
    }

    @GetMapping("/listOrderByPriceDecr")
    public List<Product> getAllProductsOrderByPriceDecr(){
        return productService.showAllProductsOrderByPriceDecr();
    }


    @GetMapping("/listNameBrands")
    public List<String> nameAllBrands() { return productService.showNameAllBrand(); }

    @GetMapping("/listNameBrandImg")
    public List<String> nameLinkAllBrand(){
        List<String> linkBrands = new ArrayList<>();
        for(String brand : productService.showNameAllBrand()){
            linkBrands.add("../assets/brand_img/" + brand + ".png");
        }
        return linkBrands;
    }

    @GetMapping("/findByBrand")
    public List<Product> getByBrand(@RequestParam(name = "brand") String brand){ return productService.showAllByBrand(brand); }

    @GetMapping("/allCategories")
    public List<String> allCategories(){ return productService.showAllCategories(); }

    @GetMapping("/findByCategory")
    public List<Product> getByCategory(@RequestParam(name = "category") String category){ return productService.showAllByCategory(category); }

    @PostMapping("/add")
    public ResponseEntity createProduct(@RequestBody @Valid Product product){
        try{
            product.setImg("../assets/product_img/" + product.getCode() + ".jpg");
            product.setLastModify(new Date());
            productService.addProduct(product);
        } catch (ProductAlreadyExistsException e){
            return new ResponseEntity<>(new ResponseMessage("Impossibile aggiungere il prodotto, già presente nel sistema."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage(("Prodotto aggiunto correttamente.")),HttpStatus.OK);
    }

    @GetMapping("/getOne")
    public ResponseEntity getProduct(@RequestParam(name = "codeProduct") String codeProduct){
        Product p;
        try{
            p=productService.showProduct(codeProduct);
        } catch (ProductNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage("Prodotto inesistente."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(p,HttpStatus.OK);
    }


    @PutMapping("/editQuantity")
    public ResponseEntity putQuantity(@RequestBody @Valid Product product){
        try{
            productService.editQuantity(product.getCode(),product.getQuantity());
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(new ResponseMessage("Errore codice prodotto."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage(("Prodotto "+product.getCode()+" modificato con quantità"+product.getQuantity())),HttpStatus.OK);
    }

    @PutMapping("/editDQP")
    public ResponseEntity putDescriptionQuantityPrice(@RequestBody @Valid Product product){
        try{
            productService.editDescriptionQuantityPrice(product.getCode(),product.getDescription(),product.getQuantity(),product.getPrice());
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(new ResponseMessage("Errore codice prodotto."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage(("Prodotto "+product.getCode()+" modificato correttamente")),HttpStatus.OK);
    }


}
