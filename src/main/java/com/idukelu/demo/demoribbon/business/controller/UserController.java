package com.idukelu.demo.demoribbon.business.controller;


import com.idukelu.demo.demoribbon.business.pojo.dto.User;
import com.idukelu.demo.demoribbon.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
        return userService.CreateUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) throws Exception {
        return userService.DeleteUser(id);
    }

    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody User user) throws Exception {
        return userService.updateUser(user);
    }

    @GetMapping("")
    public ResponseEntity<?> getUser(@RequestParam(required = false) String id, @RequestParam(required = false) String username) throws Exception {
        return userService.getUser(id, username);
    }
}