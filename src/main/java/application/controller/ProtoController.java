package application.controller;

import application.domain.Duration;
import application.domain.Place;
import application.domain.Subject;
import application.domain.User;
import application.service.tour.iface.DurationService;
import application.service.tour.iface.PlaceService;
import application.service.tour.iface.SubjectService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static application.utils.ServiceHelper.getUserFromSession;

/**
 * Загрузка данных для выпадающих списков
 * (нужны для поиска туров)
 * <p>
 * У всех потомков этого класса
 * ДО ЗАПУСКА методов!!! в Модель
 * добавляются три переменные со списками
 * Тематик, Мест и длительностей
 */
@Controller
@Setter(onMethod = @__({@Autowired}))
@Getter
public class ProtoController {
    protected static final List<Subject> subjects = new ArrayList<>();
    protected static final List<Place> places = new ArrayList<>();
    protected static final List<Duration> durations = new ArrayList<>();

    private SubjectService subjectService;
    private PlaceService placeService;
    private DurationService durationService;

    @ModelAttribute
    public void fillCollectionsForDropDowns(HttpServletRequest request, Model model) {
        User user = getUserFromSession();
        if (user != null) {
            request.getSession().setAttribute("user_name", user.getLogin());
            model.addAttribute("user", user);
        }

        model.addAttribute("subjects", subjects);
        model.addAttribute("places", places);
        model.addAttribute("durations", durations);

        synchronized (subjects) {
            if (subjects.isEmpty()) subjects.addAll(subjectService.findAllOrderByName());
        }

        synchronized (places) {
            if (places.isEmpty()) places.addAll(placeService.findAllOrderByName());
        }

        synchronized (durations) {
            if (durations.isEmpty()) durations.addAll(durationService.findAllOrderByName());
        }
    }
}
