package application.controller;

import application.domain.User;
import application.service.tour.iface.TourService;
import application.service.user.iface.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Setter(onMethod = @__({@Autowired}))
public class BillingController extends ProtoController {
    private UserService userService;
    private TourService tourService;

    @GetMapping("/pay")
    public String tour(Model model,
                       @AuthenticationPrincipal User user) {
        return "billing";
    }
}
