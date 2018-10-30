package application.controller;

import application.domain.dto.CaptchaResponseDto;
import application.domain.dto.RegistrationForm;
import application.service.user.iface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;

import static application.consts.Messages.FILL_CAPTCHA;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final RestTemplate restTemplate;

    @Value("${captcha_url}")
    private String CAPTCHA_URL;

    @Value("${captcha_secret}")
    private String captchaSecret;


    @Autowired
    public RegistrationController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/registration")
    public String registrationPage(ModelMap model,
                                   RegistrationForm registrationForm) {
        model.put("registrationForm", registrationForm);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration( @RequestParam("g-recaptcha-response") String captchaResponse,
                                @Valid RegistrationForm registrationForm,
                               BindingResult bindingResult,
                               ModelMap model) {

        String url = String.format(CAPTCHA_URL, captchaSecret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url
                                                                ,Collections.emptyList()
                                                                , CaptchaResponseDto.class);
        if (bindingResult.hasErrors() || !response.isSuccess()) {
            if(!response.isSuccess())model.addAttribute("captchaError", FILL_CAPTCHA);
            return registrationPage(model, registrationForm);
        }

        userService.registerUser(registrationForm);
        return "redirect:/";
    }
}
