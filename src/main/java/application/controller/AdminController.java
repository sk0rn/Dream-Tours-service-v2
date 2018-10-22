package application.controller;

import application.domain.*;
import application.service.tour.iface.*;
import application.utils.FtpWrite;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@Api(value = "admin!", description = "admin!!!!") // Swagger annotation
@Log4j
public class AdminController extends ProtoController {
//    TODO при любом изменении тематик в базе обнулить коллекцию subjects
//    TODO при любом изменении мест в базе обнулить коллекцию places
//    TODO при любом изменении длительностей в базе обнулить коллекцию durations

    private SessionFactory sessionFactory;
    private TourService tourService;
    private FtpWrite ftpWrite;
    private PlaceService placeService;
    private SubjectService subjectService;
    private DurationService durationService;
    private TourReleaseService tourReleaseService;
    private TourCostService tourCostService;

    @Autowired
    public AdminController(TourService tourService, FtpWrite ftpWrite, PlaceService placeService,
                           SubjectService subjectService, DurationService durationService,
                           TourReleaseService tourReleaseService, TourCostService tourCostService) {
        this.tourService = tourService;
        this.ftpWrite = ftpWrite;
        this.placeService = placeService;
        this.subjectService = subjectService;
        this.durationService = durationService;
        this.tourReleaseService = tourReleaseService;
        this.tourCostService = tourCostService;
    }

    @Autowired
    public void setSessionfacory(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @RequestMapping(value = {"/content/{tourId}", "/content"}, method = RequestMethod.GET)
    public String getEditContent(Model model,
                                 @PathVariable Optional<Long> tourId) {
        tourId.ifPresent(x -> model.addAttribute("tour", tourService.getById(x)));
        return "admin";
    }

    @RequestMapping(value = {"/content/{tourId}", "/content"}, method = RequestMethod.POST)
    public String postEditContent(Model model,
                                  //tour:
                                  @RequestParam(value = "idTour", required = false) String idTour,
                                  @RequestParam(value = "tourName", required = false) String tourName,
                                  @RequestParam(value = "descTour", required = false) String descTour,
                                  @RequestParam(value = "youtubeUrl", required = false) String youtubeUrl,
                                  @RequestParam(value = "imageTour", required = false) MultipartFile imageTour,
                                  //place:
                                  @RequestParam(value = "placeName", required = false) String placeName,
                                  @RequestParam(value = "descPlace", required = false) String descPlace,
                                  //subject:
                                  @RequestParam(value = "subjectName", required = false) String subjectName,
                                  @RequestParam(value = "descSubject", required = false) String descSubject,
                                  //duration:
                                  @RequestParam(value = "numberDays", required = false) Integer numberDays,
                                  @RequestParam(value = "nameDuration", required = false) String nameDuration,
                                  //release:
                                  @RequestParam(value = "dateStart", required = false) String dateStart,
                                  @RequestParam(value = "tourList", required = false) String tourList,
                                  @RequestParam(value = "tourDurationList", required = false) String tourDurationList,
                                  @RequestParam(value = "capacity", required = false) Integer capacity,
                                  //cost:
                                  @RequestParam(value = "tourReleaseList", required = false) String tourReleaseList,
                                  @RequestParam(value = "kind", required = false) String kind,
                                  @RequestParam(value = "tourCost", required = false) String tourCost,
                                  @RequestParam(value = "clippingAge", required = false) Integer clippingAge,
                                  @RequestParam(value = "isParticipant", required = false) String isParticipant) {
        if (tourName != null) {
            String albumGuid = null;
            if ("".equals(idTour)) {
                albumGuid = UUID.randomUUID().toString();
            } else {
                albumGuid = tourService.getById(Long.parseLong(idTour)).getAlbumGuid();
            }
            if (!"".equals(imageTour.getOriginalFilename())) {
                InputStream fileContent = null;
                try {
                    fileContent = imageTour.getInputStream();
                } catch (IOException e) {
                    log.error("Can't write file", e);
                }
                ftpWrite.writeInHost(albumGuid, "01.jpg", fileContent);
            }
            if ("".equals(idTour)) {
                tourService.add(new Tour(tourName, descTour, youtubeUrl, albumGuid.trim()));
            } else {
                tourService.update(new Tour(Long.parseLong(idTour), tourName, descTour, youtubeUrl,
                        tourService.getById(Long.parseLong(idTour)).getAlbumGuid()));
                model.addAttribute("idTour", "");
            }
        }

        if (placeName != null) {
            placeService.add(new Place(placeName, descPlace));
        }

        if (subjectName != null) {
            subjectService.add(new Subject(subjectName, descSubject));
        }

        if (numberDays != null) {
            durationService.add(new Duration(numberDays, nameDuration));
        }

        if (dateStart != null) {
            tourReleaseService.add(new TourRelease(tourService.getById(Long.parseLong(tourList)),
                    durationService.getById(Long.parseLong(tourDurationList)),
                    Timestamp.valueOf(dateStart.replace("T", " ") + ":00"),
                    capacity));
            return "redirect:/admin/content";
        }

        if (tourCost != null) {
            Boolean kindCost = false;
            if ("перелет".equals(kind)) {
                kindCost = true;
            }
            Boolean isParticipantCost = false;
            if ("on".equals(isParticipant)) {
                isParticipantCost = true;
            }
            tourCostService.add(new TourCost(tourReleaseService.getById(Long.parseLong(tourReleaseList)),
                    kindCost, Double.parseDouble(tourCost), clippingAge, isParticipantCost));
            return "redirect:/admin/content";
        }
        return "admin";
    }
}
