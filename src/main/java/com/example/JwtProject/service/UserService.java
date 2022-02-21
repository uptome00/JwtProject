package com.example.JwtProject.service;

import com.example.JwtProject.domain.Role;
import com.example.JwtProject.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void  addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User>getUsers();
}
