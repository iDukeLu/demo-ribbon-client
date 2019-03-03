package com.idukelu.demo.demoribbon.business.service;

import com.idukelu.demo.demoribbon.business.pojo.dto.User;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<?> createUser(User user) throws Exception;

    ResponseEntity<?> deleteUser(String id) throws Exception;

    ResponseEntity<?> updateUser(User user) throws Exception;

    ResponseEntity<?> getUser(String id, String username) throws Exception;
}
