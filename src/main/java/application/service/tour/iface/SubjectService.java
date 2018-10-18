package application.service.tour.iface;

import application.domain.Subject;

import java.util.List;

public interface SubjectService {
    boolean add(Subject subject);

    Subject getById(Integer id);

    List<Subject> getAll();

    boolean updateById(Subject subject);

    boolean deleteById(Integer id);
}
