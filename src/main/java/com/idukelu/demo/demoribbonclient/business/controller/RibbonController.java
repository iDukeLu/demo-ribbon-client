package com.idukelu.demo.demoribbonclient.business.controller;


import com.idukelu.demo.demoribbonclient.business.pojo.dto.User;
import com.idukelu.demo.demoribbonclient.business.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RibbonController {

    private RibbonService ribbonService;

    @Autowired
    public RibbonController(RibbonService ribbonService) {
        this.ribbonService = ribbonService;
    }


    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
        return ribbonService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) throws Exception {
        return ribbonService.deleteUser(id);
    }

    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody User user) throws Exception {
        return ribbonService.updateUser(user);
    }

    @GetMapping("")
    public ResponseEntity<?> getUser(@RequestParam(required = false) String id, @RequestParam(required = false) String username) throws Exception {
        return ribbonService.getUser(id, username);
    }
}
