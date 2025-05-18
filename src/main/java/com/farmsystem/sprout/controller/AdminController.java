package com.farmsystem.sprout.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @GetMapping("/")
    public String test() {
        return "🔥 서버 잘 켜졌습니다 🔥";
    }
}
