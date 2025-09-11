package com.lldproject.bookmyshow.service;

import com.lldproject.bookmyshow.model.User;
import com.lldproject.bookmyshow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUpUser(String name, String email, String password) throws Exception{
        Optional<User> userOp = userRepository.findByEmail(email);
        if(userOp.isPresent()){
            throw new Exception("User already exists");
        }
        //Create a new user
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public boolean loginUser(String email, String password) throws Exception{
        Optional<User> userOp = userRepository.findByEmail(email);
        if(userOp.isEmpty()){
            throw new Exception("User not registered");
        }
        User user = userOp.get();
        return user.getPassword().equals(password);
    }
}
