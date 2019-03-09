package com.idukelu.demo.demoribbonclient.business.service.impl;

import com.idukelu.demo.demoribbonclient.business.pojo.dto.User;
import com.idukelu.demo.demoribbonclient.business.service.RibbonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonServiceImpl implements RibbonService {

    private RestTemplate restTemplate;

    @Autowired
    public RibbonServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod="createUserFailback")
    public ResponseEntity<?> createUser(User user) throws Exception{
        return ResponseEntity.ok(restTemplate.postForObject("http://DEMO-EUREKA-CLIENT/eurekasvr", user, User.class));
    }

    @HystrixCommand(fallbackMethod="deleteUserFailback")
    public ResponseEntity<?> deleteUser(String id) throws Exception {
        restTemplate.delete("http://DEMO-EUREKA-CLIENT/eurekasvr/1");
        return ResponseEntity.ok(id);
    }

    @HystrixCommand(fallbackMethod="updateUserFailback")
    public ResponseEntity<?> updateUser(User user) throws Exception {
        restTemplate.put("http://DEMO-EUREKA-CLIENT/eurekasvr", user, User.class);
        return ResponseEntity.ok(user);
    }

    @HystrixCommand(fallbackMethod="getUserFailback")
    public ResponseEntity<?> getUser(String id, String username) throws Exception {
        return ResponseEntity.ok(restTemplate.getForObject("http://DEMO-EUREKA-CLIENT/eurekasvr?id={1}", User.class, id));
    }

    public ResponseEntity<?> createUserFailback(User user, Throwable e) throws Exception{
        return ResponseEntity.ok("创建用户失败！！！");
    }

    public ResponseEntity<?> deleteUserFailback(String id, Throwable e) throws Exception{
        return ResponseEntity.ok("删除用户失败！！！");
    }

    public ResponseEntity<?> updateUserFailback(User user, Throwable e) throws Exception{
        return ResponseEntity.ok("修改用户失败！！！");
    }

    public ResponseEntity<?> getUserFailback(String id, String username, Throwable e) throws Exception{
        return ResponseEntity.ok("查询用户失败！！！");
    }
}
