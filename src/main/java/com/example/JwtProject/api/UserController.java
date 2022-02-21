package com.example.JwtProject.api;

import com.example.JwtProject.domain.Role;
import com.example.JwtProject.domain.User;
import com.example.JwtProject.service.UserService;
import com.example.JwtProject.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>>getUserByUsername(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/add")
    public ResponseEntity<User>addUser(@RequestBody User user){
        return ResponseEntity.created(null).body(userService.saveUser(user));
    }

    @PostMapping("/role/add")
    public ResponseEntity<Role>addRole(@RequestBody Role role){
        return ResponseEntity.created(null).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }
}
@Data
class RoleToUserForm{
    private String username;
    private String rolename;
}
