package dev.ayush.Splitwise.Controller;

import dev.ayush.Splitwise.dto.UserLoginRequestDTO;
import dev.ayush.Splitwise.dto.UserRegistrationRequestDTO;
import dev.ayush.Splitwise.entity.User;
import dev.ayush.Splitwise.exception.UserRegistrationInvalidDataException;
import dev.ayush.Splitwise.mapper.EntityDTOMapper;
import dev.ayush.Splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO){
        validateUserRegistrationRequestDTO(userRegistrationRequestDTO);
        User savedUser = userService.signUp(userRegistrationRequestDTO.getName(),
                userRegistrationRequestDTO.getEmail(),
                userRegistrationRequestDTO.getPassword());
        return ResponseEntity.ok(
                EntityDTOMapper.toDTO(savedUser)
        );
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDTO userLoginRequestDTO){
        validateUserLoginRequestDTO(userLoginRequestDTO);
        User savedUser = userService.login(userLoginRequestDTO.getEmail(), userLoginRequestDTO.getPassword());
        return ResponseEntity.ok(EntityDTOMapper.toDTO(savedUser));
    }

    private void validateUserRegistrationRequestDTO(UserRegistrationRequestDTO requestDTO){
        if(requestDTO.getName() == null ||
        requestDTO.getEmail() == null ||
        requestDTO.getPassword() == null){
            throw new UserRegistrationInvalidDataException("Invalid SignUp Data.");
        }
    }

    private void validateUserLoginRequestDTO(UserLoginRequestDTO userLoginRequestDTO){

    }
}
