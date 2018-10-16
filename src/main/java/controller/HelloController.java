package controller;

import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

    @GetMapping
    public String hello() {
        return "Greetings from Spring Boot";
    }
}