package com.spring_security.usap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // Anyone can access this endpoint
    @GetMapping("/")
    public String sayHello() {
        return "Welcome to Spring Security!";
    }

    // Both ADMIN and USER can access this endpoint
    @GetMapping("/user")
    public String userPage() {
        return "This is the USER Page. (Visible to ADMIN and USER)";
    }

    // Only ADMIN can access this endpoint
    @GetMapping("/admin")
    public String adminPage() {
        return "This is the ADMIN Page. (Visible ONLY to ADMIN)";
    }

}
