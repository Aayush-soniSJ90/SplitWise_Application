package dev.ayush.Splitwise.service;

import dev.ayush.Splitwise.entity.User;
import dev.ayush.Splitwise.exception.InvalidCredentialException;
import dev.ayush.Splitwise.exception.UserDoesNotExistException;
import dev.ayush.Splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIMPL implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signUp(String name, String email, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user= new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        User savedUser = userRepository.findUseByEmail(email);
        if(savedUser == null){
            throw new UserDoesNotExistException("User Not Found");
        }
        if(encoder.matches(password, savedUser.getPassword())){
            return savedUser;
        }else{
            throw new InvalidCredentialException("Invalid Credentials");
        }
    }
}
