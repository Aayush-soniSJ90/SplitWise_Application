package dev.ayush.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class UserLoginRequestDTO {
    private String email;
    private String password;

}
