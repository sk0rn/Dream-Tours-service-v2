package application.service.tour.iface;

import application.domain.Subject;

import java.util.List;

public interface SubjectService {
    boolean add(Subject subject);

    Subject getById(Long id);

    List<Subject> getAll();

    boolean updateById(Subject subject);

    boolean deleteById(Long id);

    List<Subject> findAllOrderByName();
}
