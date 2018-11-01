package application.controller;

import application.service.tour.iface.TourService;
import application.service.user.iface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static application.consts.Consts.*;
import static application.utils.ServiceHelper.getUserFromSession;

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
    public String tour(Model model
                       //TODO добавить после включения security
            /*, @AuthenticationPrincipal User user*/) {
        return "customer";
    }

    @PostMapping(value = "/addInWishlist")
    public String addImWishList(@RequestParam(name = "idTour") long idTour,
                                @RequestParam(name = "operation") int add,
                                Model model) {
        switch (add) {
            case ADD_TOUR_TO_WISH_LIST_OPERATION:
                model.addAttribute(OPERATION_RESULT,
                        tourService.addUserToSetUsers(idTour,
                                getUserFromSession()) ? ADD : ERROR);
                break;

            case REMOVE_TOUR_FROM_WISH_LIST_OPERATION:
                model.addAttribute(OPERATION_RESULT,
                        tourService.removeUserFromSetUsers(idTour,
                                getUserFromSession()) ? REMOVE : ERROR);
                break;

            default:
                model.addAttribute(OPERATION_RESULT, ERROR);
                break;
        }

        return OPERATION_RESULT;
    }
}
