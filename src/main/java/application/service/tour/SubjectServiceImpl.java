package application.service.tour;

import application.domain.Subject;
import application.repository.SubjectRepository;
import application.service.tour.iface.SubjectService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public boolean add(Subject subject) {
        return ServiceHelper.save(subjectRepository, subject);
    }

    @Override
    public Subject getById(Long id) {
        return ServiceHelper.getById(subjectRepository, id);
    }

    @Override
    public List<Subject> getAll() {
        return ServiceHelper.getAll(subjectRepository);
    }

    @Override
    public boolean updateById(Subject subject) {
        return ServiceHelper.save(subjectRepository, subject);
    }

    @Override
    public boolean deleteById(Long id) {
        return ServiceHelper.delete(subjectRepository, id);
    }
}
