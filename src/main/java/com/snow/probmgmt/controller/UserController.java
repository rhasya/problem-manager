package com.snow.probmgmt.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class UserController {
    
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/you")
    public Map<String, String> getYou(@AuthenticationPrincipal UserDetails user) {
        HashMap<String, String> userInfo = new HashMap<>();
        if (user != null) {
            log.debug(user.toString());
            userInfo.put("username", user.getUsername());
        } else {
            log.debug("There is no signing in user");
        }
        return userInfo;
    }
}
