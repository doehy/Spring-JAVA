package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //RestAPI용 컨트롤러! 일반적으로 JSON을 반환!
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hellp() {
        return "hello world";
    }
}
