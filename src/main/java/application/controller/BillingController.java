package application.controller;

import application.domain.User;
import application.repository.UserRepository;
import application.service.tour.iface.TourService;
import application.service.user.iface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BillingController extends ProtoController {

    private final UserRepository userRepository;
    private UserService userService;
    private TourService tourService;

    @Autowired
    public BillingController(UserRepository userRepository, UserService userService, TourService tourService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.tourService = tourService;
    }

    @GetMapping("/pay")
    public String tour(Model model,
                       @AuthenticationPrincipal User user) {
        return "billing";
    }
}
