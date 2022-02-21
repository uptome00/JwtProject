package com.example.JwtProject.repository;

import com.example.JwtProject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {

    User findByUsername(String username);
}
