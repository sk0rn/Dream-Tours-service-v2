package application.controller;

import application.domain.Tour;
import application.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ToursController {

    private final TourRepository tourRepository;

    @Autowired
    public ToursController(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @GetMapping(value = {"/tours", "/"})
    public String tours(Model model) {
        List<Tour> tours = tourRepository.findAll();
        model.addAttribute("tours", tours);
        return "tours";
    }
}