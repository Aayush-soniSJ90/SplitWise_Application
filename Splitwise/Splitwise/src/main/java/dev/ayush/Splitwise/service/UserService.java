package dev.ayush.Splitwise.service;

import dev.ayush.Splitwise.entity.User;

public interface UserService {
    User signUp(String name, String email, String password);
    User login(String email, String password);
}
