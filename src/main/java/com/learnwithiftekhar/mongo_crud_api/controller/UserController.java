package com.learnwithiftekhar.mongo_crud_api.controller;

import com.learnwithiftekhar.mongo_crud_api.model.User;
import com.learnwithiftekhar.mongo_crud_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE - POST /api/users
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        // check if the user with the email already exists
        if(userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        var savedUser = userService.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }

    // READ - GET /api/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userList);
    }

    // READ - GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id).orElse(null);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    // UPDATE - PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id,
                                           @RequestBody User user) {
        if(!userService.getUserById(id).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        var updatedUser = userService.updateUser(id, user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedUser);
    }

    // DELETE - DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        var deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.status(HttpStatus.OK).body("User deleted successfully") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to delete the user");
    }

}
