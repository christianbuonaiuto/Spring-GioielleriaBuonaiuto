package com.gioielleriabuonaiuto.service;

import com.gioielleriabuonaiuto.entity.User;
import com.gioielleriabuonaiuto.repository.UserRepository;
import com.gioielleriabuonaiuto.support.exception.UserAlreadyExistsException;
import com.gioielleriabuonaiuto.support.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public boolean isAdmin(String emailUser)throws UserNotFoundException{
        if(!userRepository.existsByEmail(emailUser)){
            throw new UserNotFoundException();
        }
        return userRepository.getByEmail(emailUser).isAdmin();
    }

    @Transactional(readOnly = true)
    public List<User> showAllUsers(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = false)
    public void addUser(User user) throws UserAlreadyExistsException{
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException();
        }
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User showUser(String userEmail) throws UserNotFoundException{
        if(!userRepository.existsByEmail(userEmail)){
            throw new UserNotFoundException();
        }
        return userRepository.getByEmail(userEmail);
    }

    @Transactional(readOnly = false)
    public void editPhoneAddressPostalCode(String emailUser, String phone, String address, long pcode, boolean role) throws UserNotFoundException{
        if(!userRepository.existsByEmail(emailUser)){
            throw new UserNotFoundException();
        }
        User u = userRepository.getByEmail(emailUser);
        u.setPhonenumber(phone);
        u.setStreetaddress(address);
        u.setPostalcode(pcode);
    }

    @Transactional(readOnly = false)
    public void editAdmin(String emailUser, boolean role) throws UserNotFoundException{
        if(!userRepository.existsByEmail(emailUser)){
            throw new UserNotFoundException();
        }
        User u = userRepository.getByEmail(emailUser);
        u.setAdmin(role);
    }

}
