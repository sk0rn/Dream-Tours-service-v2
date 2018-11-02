package application.controller;

import application.service.tour.iface.TourService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static application.consts.Consts.OPERATION_RESULT;

@Controller
@Setter(onMethod = @__({@Autowired}))
public class UserController extends ProtoController {
    private TourService tourService;

    @GetMapping("/profile")
    public String tour(Model model) {
        //нам нужна актуальная информация по
        return "customer";
    }

    @PostMapping(value = "/addToWishlist")
    public String addImWishList(@RequestParam(name = "idTour") long idTour,
                                @RequestParam(name = "operation") int operation,
                                Model model) {
        model.addAttribute(OPERATION_RESULT, tourService.modifyWishList(idTour, operation));
        return OPERATION_RESULT;
    }
}
