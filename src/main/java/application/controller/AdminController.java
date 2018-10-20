package application.controller;

import application.repository.TourRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
//@RequestMapping("/admin")
@Api(value = "admin!", description = "admin!!!!") // Swagger annotation
public class AdminController {

    TourRepository tourRepository;

    @Autowired
    public AdminController(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @GetMapping({"/content/{tourId}", "/content/"})
    public String getEditContent(Model model, @PathVariable Optional<Long> tourId) {
        tourId.ifPresent(x -> model.addAttribute("tour", tourRepository.findOneById(x)));
        return "admin";
    }

    @PostMapping({"/content/{tourId}", "/content/"})
    public String postEditContent(Model model, @PathVariable Optional<Integer> tourId) {
        //TODO realize
        return "admin";
    }

    @GetMapping("/admin")
    public String tour(Model model) {
        return "admin";
    }
}
