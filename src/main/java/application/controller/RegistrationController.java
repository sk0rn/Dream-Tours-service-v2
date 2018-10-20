package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

    @RequestMapping("/reg")
    public String tour(Model model) {
        return "reg";
    }
}
