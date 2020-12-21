package com.gioielleriabuonaiuto.controller;

import com.gioielleriabuonaiuto.entity.User;
import com.gioielleriabuonaiuto.service.UserService;
import com.gioielleriabuonaiuto.support.ResponseMessage;
import com.gioielleriabuonaiuto.support.exception.UserAlreadyExistsException;
import com.gioielleriabuonaiuto.support.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.showAllUsers();
    }

    @GetMapping("/isAdmin")
    public ResponseEntity getIsAdmin(@RequestParam(name = "emailUser") String emailUser){
        boolean a;
        try{
            a = userService.isAdmin(emailUser);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage("Impossibile trovare l'utente."),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(a,HttpStatus.OK);
    }

    @PutMapping("/changeRole")
    public ResponseEntity putRole(@RequestBody @Valid User user){
        try{
            userService.editAdmin(user.getEmail(),user.isAdmin());
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage("Impossibile trovare l'utente"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage("Utente "+ user.getEmail() + " cambiato di ruolo"),HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity createUser(@RequestBody @Valid User user){
        try{
            userService.addUser(user);
        } catch (UserAlreadyExistsException e){
            return new ResponseEntity<>(new ResponseMessage("Impossibile aggiungere l'utente, già presente nel sistema."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage("Utente inserito correttamente."),HttpStatus.OK);
    }

    @GetMapping("/getOne")
    public ResponseEntity getUser(@RequestParam(name = "emailUser") String emailUser){
        User u;
        try{
            u=userService.showUser(emailUser);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage("L'utente non esiste !"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    @PutMapping("/editPAP")
    public ResponseEntity putPhoneAddressPostalCode(@RequestBody @Valid User user){
        try{
            userService.editPhoneAddressPostalCode(user.getEmail(),user.getPhonenumber(),user.getStreetaddress(),user.getPostalcode(),user.isAdmin());
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage("Impossibile trovare l'utente"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage("Utente "+ user.getEmail() + "modificato correttamente"),HttpStatus.OK);
    }



}
