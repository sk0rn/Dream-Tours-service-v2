package application.controller;

import application.domain.Tour;
import application.domain.User;
import application.repository.UserRepository;
import application.service.tour.iface.TourService;
import application.service.user.iface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController extends ProtoController {

    private final UserRepository userRepository;
    private UserService userService;
    private TourService tourService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, TourService tourService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.tourService = tourService;
    }

    @GetMapping("/profile")
    public String tour(Model model
                       //TODO добавить после включения security
            /*, @AuthenticationPrincipal User user*/) {
        return "customer";
    }

    @RequestMapping(value = "/addInWishlist", method = RequestMethod.POST)
    public String addImWishList(@RequestParam(name = "idTour") String idTour) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getName();
        User user = userService.getByLogin(auth.getName());
        Tour tour = tourService.getById(Long.parseLong(idTour));
        userService.addTourInSetTours(user, tour);
        tourService.addUserInSetUsers(tour, user);
        userService.update(user);
        tourService.update(tour);
        return "redirect:/tours";
    }
}
