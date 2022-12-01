package com.niit.userProductServicepc1.proxy;

import com.niit.userProductServicepc1.domain.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentication-service",url = "http://authentication-service:9200/")
public interface UserProxy {
        @PostMapping("/userservice/user/register")
        public ResponseEntity<?> saveUser(@RequestBody UserModel userModel);
}
