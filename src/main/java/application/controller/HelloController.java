package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "customer";
    }

    @RequestMapping("/tours")
    public String tours() {
        return "tours";
    }
}