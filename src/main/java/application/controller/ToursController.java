package application.controller;

import application.domain.Tour;
import application.service.tour.iface.DurationService;
import application.service.tour.iface.PlaceService;
import application.service.tour.iface.SubjectService;
import application.service.tour.iface.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ToursController {
    @Value("${remote-connection-host}")
    private String remoteConnectionHost;

    @Autowired
    private TourService tourService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private DurationService durationService;

    @GetMapping(value = {"/tours", "/"})
    public String tours(Model model) {
        List<Tour> tours = tourService.getAll();
        model.addAttribute("tours", tours);
        return internalFillLists(model);
    }

    @GetMapping("/tours/{tourId}")
    public String tour(Model model,
                       @PathVariable Long tourId) {
        Tour tour = tourService.getById(tourId);
        model.addAttribute("tour", tour);
        model.addAttribute("remoteConnectionHost", remoteConnectionHost);
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
                            @RequestParam(name = "duration", required = false, defaultValue = "-1") String duration
    ) {
        if (subjectId.equals("-1") &&
                placeId.equals("-1") &&
//        TODO Раскоментить вишлист, когда с безопасностью срастётся
                //inWishList.equals("0") &&
                searchString.isEmpty() &&
//                TODO сделать на форме нормальные контролы для заполнения даты и парсить их здесь
                //dateBegin.isEmpty() &&
                //dateEnd.isEmpty() &&
                costFrom.isEmpty() &&
                costTo.isEmpty() &&
                duration.equals("-1")
        ) {
            return tours(model);
        } else {
//        TODO Добавить юзера когда с безопасностью срастётся
            model.addAttribute("tours", tourService.superPuperDuperSearch(-1L,
                    subjectId, placeId, inWishList, searchString,
//                TODO сделать на форме нормальные контролы для заполнения даты и парсить их здесь
                    null/*dateBegin*/, null/*dateEnd*/,
                    costFrom, costTo, duration)
            );
            return internalFillLists(model);
        }
    }

    private String internalFillLists(Model model) {
        model.addAttribute("remoteConnectionHost", remoteConnectionHost);

        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("places", placeService.getAll());
        model.addAttribute("durations", durationService.getAll());

        return "tours";
    }
}
