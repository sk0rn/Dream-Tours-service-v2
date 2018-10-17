package application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String hello() {
//        LoginService tourService = new LoginServiceImpl(); //ctx.getBean(LoginService.class);
        return "Greetings from Spring Boot";
    }
}