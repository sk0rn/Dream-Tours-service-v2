package application.controller;

import application.domain.Tour;
import application.service.tour.iface.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static application.utils.ServiceHelper.getUserIdFromSession;

@Controller
public class ToursController extends ProtoController {
    private final TourService tourService;

    @Value("${remote-connection-host}")
    private String remoteConnectionHost;

    @Autowired
    public ToursController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping(value = {"/tours", "/"})
    public String tours(Model model) {
        model.addAttribute("tours", tourService.getAll());
        return "tours";
    }

    @GetMapping("/tour/{tourId}")
    public String tour(Model model,
                       @PathVariable Long tourId) {
        Tour tour = tourService.getById(tourId);
        model.addAttribute("tour", tour);
        return "tour";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/tours")
    public String findTours(Model model,
                            //Если заданы только первые четыре параметра, то поиск идёт просто по турам
                            @RequestParam(name = "subjectId", required = false, defaultValue = "-1") String subjectId,
                            @RequestParam(name = "placeId", required = false, defaultValue = "-1") String placeId,
                            @RequestParam(name = "inWishList", required = false, defaultValue = "0") String inWishList,
                            @RequestParam(name = "searchString", required = false, defaultValue = "") String searchString,

                            //Если же задано хотя бы одно из оставшихся пяти полей,
                            //то поиск должен уже идти среди релизов
                            @RequestParam(name = "dateBegin", required = false, defaultValue = "") String dateBegin,
                            @RequestParam(name = "dateEnd", required = false, defaultValue = "") String dateEnd,
                            @RequestParam(name = "costFrom", required = false, defaultValue = "") String costFrom,
                            @RequestParam(name = "costTo", required = false, defaultValue = "") String costTo,
                            @RequestParam(name = "durationFrom", required = false, defaultValue = "") String durationFrom,
                            @RequestParam(name = "durationTo", required = false, defaultValue = "") String durationTo,
                            @RequestParam(name = "durationId", required = false, defaultValue = "-1") String duration
    ) {
        model.addAttribute("tours",
                (subjectId.equals("-1") &&
                        placeId.equals("-1") &&
                        inWishList.equals("0") &&
                        searchString.isEmpty() &&
//                TODO сделать на форме нормальные контролы для заполнения даты и парсить их здесь
                        //dateBegin.isEmpty() &&
                        //dateEnd.isEmpty() &&
                        costFrom.isEmpty() &&
                        costTo.isEmpty() &&
                        duration.equals("-1")
                ) ?
                        tourService.getAll() :
                        tourService.complexQuery(subjectId, placeId, inWishList, searchString,
//                TODO сделать на форме нормальные контролы для заполнения даты и парсить их здесь
                                null/*dateBegin*/, null/*dateEnd*/,
                                costFrom, costTo, duration));
        model.addAttribute("subjectIdValue", subjectId);
        model.addAttribute("placeIdValue", placeId);
        model.addAttribute("durationIdValue", duration);
        model.addAttribute("inWishListValue", inWishList);
        model.addAttribute("dateBeginValue", dateBegin);
        model.addAttribute("dateEndValue", dateEnd);
        model.addAttribute("costFromValue", costFrom);
        model.addAttribute("costToValue", costTo);
        model.addAttribute("durationFromValue", durationFrom);
        model.addAttribute("durationToValue", durationTo);
        model.addAttribute("searchStringValue", searchString);
        return "tours";
    }

    @ModelAttribute(value = "remoteConnectionHost")
    public String internalFillConnectionHost() {
        return remoteConnectionHost;
    }

    @ModelAttribute(value = "wishList")
    public Set<Long> internalFillWishList() {
        return tourService.getWishList(getUserIdFromSession());
    }
}
