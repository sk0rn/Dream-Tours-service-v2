package application.controller;

import application.domain.User;
import application.domain.dto.RegistrationForm;
import application.service.user.iface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(ModelMap model, RegistrationForm registrationForm) {
        model.put("registrationForm", registrationForm);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid RegistrationForm registrationForm, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return registrationPage(model, registrationForm);
        }
        userService.registerUser(registrationForm);
        return "redirect:/";
    }
}
