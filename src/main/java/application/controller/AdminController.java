package application.controller;

import application.domain.Tour;
import application.repository.TourRepository;
import io.swagger.annotations.Api;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManagerFactory;
import javax.validation.Valid;
import java.util.Optional;

//TODO use Admin preAuthorize for auth
@Controller
@RequestMapping("/admin")
@Api(value = "admin!", description = "admin!!!!") // Swagger annotation
public class AdminController {

    private SessionFactory sessionFactory;
    private TourRepository tourRepository;

    @Autowired
    public AdminController(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Autowired
    public void setSessionfacory(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @GetMapping({"/content/{tourId}", "/content"})
    public String getEditContent(Model model, @PathVariable Optional<Long> tourId) {
        tourId.ifPresent(x -> model.addAttribute("tour", tourRepository.findOneById(x)));
        return "admin";
    }

    @PostMapping({"/content/{tourId}", "/content/"})
    public String postEditContent(Model model,
                                  @PathVariable Optional<Long> tourId,
                                  @Valid Tour tour) {
        if (!tourId.isPresent()) {
            tourRepository.saveAndFlush(tour);
        } else {
            tour.setId(tourId.get());
            tourRepository.saveAndFlush(tour);
        }
        return "admin";
    }
}