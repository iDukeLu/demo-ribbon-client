package com.idukelu.demo.demoribbon.business.service.impl;

import com.idukelu.demo.demoribbon.business.pojo.dto.User;
import com.idukelu.demo.demoribbon.business.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    public ResponseEntity<?> CreateUser(User user) throws Exception{
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> DeleteUser(String id) throws Exception {
        return ResponseEntity.ok(id);
    }

    public ResponseEntity<?> updateUser(User user) throws Exception {
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> getUser(String id, String username) throws Exception {
        User user = new User();
        user.setId(id).setUsername(username);

        return ResponseEntity.ok(user);
    }
}
