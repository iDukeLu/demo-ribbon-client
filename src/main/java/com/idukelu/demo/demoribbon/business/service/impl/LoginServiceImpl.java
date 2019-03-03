package com.idukelu.demo.demoribbon.business.service.impl;

import com.idukelu.demo.demoribbon.business.pojo.dto.User;
import com.idukelu.demo.demoribbon.business.service.LoginService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginServiceImpl implements LoginService {

    private RestTemplate restTemplate;

    @Autowired
    public LoginServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod="createUserError")
    public ResponseEntity<?> createUser(User user) throws Exception{
        return ResponseEntity.ok(restTemplate.postForObject("http://DEMO-EUREKA-CLIENT/user", user, User.class));
    }

    @HystrixCommand(fallbackMethod="deleteUserError")
    public ResponseEntity<?> deleteUser(String id) throws Exception {
        restTemplate.delete("http://DEMO-EUREKA-CLIENT/user/1");
        return ResponseEntity.ok(id);
    }

    @HystrixCommand(fallbackMethod="updateUserError")
    public ResponseEntity<?> updateUser(User user) throws Exception {
        restTemplate.put("http://DEMO-EUREKA-CLIENT/user", user, User.class);
        return ResponseEntity.ok(user);
    }

    @HystrixCommand(fallbackMethod="getUserError")
    public ResponseEntity<?> getUser(String id, String username) throws Exception {
        return ResponseEntity.ok(restTemplate.getForObject("http://DEMO-EUREKA-CLIENT/user?id="+id, User.class));
    }

    public ResponseEntity<?> createUserError(User user) throws Exception{
        return ResponseEntity.ok("创建用户失败！！！");
    }

    public ResponseEntity<?> deleteUserError(String id) throws Exception{
        return ResponseEntity.ok("删除用户失败！！！");
    }

    public ResponseEntity<?> updateUserError(User user) throws Exception{
        return ResponseEntity.ok("修改用户失败！！！");
    }

    public ResponseEntity<?> getUserError(String id, String username) throws Exception{
        return ResponseEntity.ok("查询用户失败！！！");
    }
}
