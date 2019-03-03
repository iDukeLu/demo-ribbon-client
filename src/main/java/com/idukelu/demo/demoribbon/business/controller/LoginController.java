package com.idukelu.demo.demoribbon.business.controller;


import com.idukelu.demo.demoribbon.business.pojo.dto.User;
import com.idukelu.demo.demoribbon.business.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
        return loginService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) throws Exception {
        return loginService.deleteUser(id);
    }

    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody User user) throws Exception {
        return loginService.updateUser(user);
    }

    @GetMapping("")
    public ResponseEntity<?> getUser(@RequestParam(required = false) String id, @RequestParam(required = false) String username) throws Exception {
        return loginService.getUser(id, username);
    }
}
