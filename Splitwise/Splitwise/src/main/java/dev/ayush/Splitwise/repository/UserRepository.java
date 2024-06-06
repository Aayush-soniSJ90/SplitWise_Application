package dev.ayush.Splitwise.repository;

import dev.ayush.Splitwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUseByEmail(String email);
}
