package application.controller;

import application.service.tour.iface.TourService;
import application.service.user.iface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static application.consts.Consts.OPERATION_RESULT;

@Controller
public class UserController extends ProtoController {
    private UserService userService;
    private TourService tourService;

    @Autowired
    public UserController(UserService userService, TourService tourService) {
        this.userService = userService;
        this.tourService = tourService;
    }

    @GetMapping("/profile")
    public String tour(Model model) {
        //нам нужна актуальная информация по
        return "customer";
    }

    @PostMapping(value = "/addInWishlist")
    public String addImWishList(@RequestParam(name = "idTour") long idTour,
                                @RequestParam(name = "operation") int operation,
                                Model model) {
        model.addAttribute(OPERATION_RESULT, tourService.modifyWishList(idTour, operation));
        return OPERATION_RESULT;
    }
}
