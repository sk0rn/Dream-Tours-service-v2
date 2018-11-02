package application.controller;

import application.domain.*;
import application.service.tour.iface.*;
import application.utils.FtpWrite;
import io.swagger.annotations.Api;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@Api(value = "admin!", description = "admin!!!!") // Swagger annotation
@Log4j
@Setter(onMethod = @__({@Autowired}))
public class AdminController extends ProtoController {
//    TODO при любом изменении тематик в базе обнулить коллекцию subjects
//    TODO при любом изменении мест в базе обнулить коллекцию places
//    TODO при любом изменении длительностей в базе обнулить коллекцию durations

    private TourService tourService;
    private FtpWrite ftpWrite;
    private PlaceService placeService;
    private SubjectService subjectService;
    private DurationService durationService;
    private ReleaseService releaseService;
    private CostService costService;
    private AlbumService albumService;

    @GetMapping(value = {"/content/{tourId}", "/content"})
    public String getEditContent(Model model,
                                 @PathVariable Optional<Long> tourId) {
        tourId.ifPresent(x -> model.addAttribute("tour", tourService.getById(x)));
        model.addAttribute("tours", tourService.getAll());
        model.addAttribute("releases", releaseService.getAll());
        return "admin";
    }

    @PostMapping(value = {"/content/{tourId}", "/content"})
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
                                  @RequestParam(value = "cost", required = false) String cost,
                                  @RequestParam(value = "clippingAge", required = false) Integer clippingAge,
                                  @RequestParam(value = "isParticipant", required = false) String isParticipant) {
        if (tourName != null) {
            Album albumGuid = new Album();
            if ("".equals(idTour)) {
                albumGuid.setName(UUID.randomUUID().toString());
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

                ftpWrite.writeInHost(albumGuid, imageTour.getOriginalFilename(), fileContent);
                albumGuid.addFile(new File(imageTour.getOriginalFilename()));
            }
            if ("".equals(idTour)) {
                albumGuid.setName(albumGuid.getName().trim());
                albumService.add(albumGuid);
                tourService.add(new Tour(tourName, descTour, youtubeUrl, albumGuid));
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
            releaseService.add(new Release(tourService.getById(Long.parseLong(tourList)),
                    durationService.getById(Long.parseLong(tourDurationList)),
                    Timestamp.valueOf(dateStart.replace("T", " ") + ":00"),
                    capacity));
            return "redirect:/admin/content";
        }

        if (cost != null) {
            Boolean kindCost = false;
            if ("перелет".equals(kind)) {
                kindCost = true;
            }
            Boolean isParticipantCost = false;
            if ("on".equals(isParticipant)) {
                isParticipantCost = true;
            }
            costService.add(new Cost(releaseService.getById(Long.parseLong(tourReleaseList)),
                    kindCost, Double.parseDouble(cost), clippingAge, isParticipantCost));
            return "redirect:/admin/content";
        }
        return getEditContent(model, Optional.of(Long.parseLong(idTour)));
    }

    @ModelAttribute
    public void fillCollectionsForDropDowns(Model model) {
        model.addAttribute("tours", tourService.getAll());
        model.addAttribute("releases", releaseService.getAll());
    }
}