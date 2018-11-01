package application.utils;

import application.domain.User;
import lombok.extern.log4j.Log4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Log4j
public final class ServiceHelper {

    public static long getUserIdFromSession() {
        User user = getUserFromSession();
        return user == null ? -1 : user.getId();
    }

    public static User getUserFromSession() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user instanceof User ? (User) user : null;
    }

    public static <T> List<T> getAll(FinderNoParam<T> finder) {
        List<T> tours;
        return (tours = finder.execute()) == null ?
                Collections.emptyList() :
                tours;
    }

    public static <T, P> List<T> getListByParam(Finder<T, P> finder, P param) {
        List<T> entities;
        return param == null ||
                (entities = finder.execute(param)) == null ?
                Collections.emptyList() :
                entities;
    }

    public static <T, P> T getEntityByParam(Getter<T, P> getter, P param) {
        return param == null ? null : getter.execute(param);
    }

    private ServiceHelper() {
    }

    public static <T> boolean delete(JpaRepository<T, Long> repository, Long id) {
        if (id != null &&
                id > 0 &&
                repository.existsById(id)) {
            repository.deleteById(id);
            return !repository.existsById(id);
        }
        return false;
    }

    public static <T> List<T> getAll(JpaRepository<T, Long> repository) {
        List<T> tours;
        return (tours = repository.findAll()) == null ?
                Collections.emptyList() :
                tours;
    }

    public static <T> T getById(Getter<T, Long> getter, Long id) {
        return id == null ||
                id < 1 ?
                null :
                getter.execute(id);
    }

    public static <T> boolean save(JpaRepository<T, Long> repository, T entity) {
        return entity != null && repository.save(entity) != null;
    }

    public static long stringToId(String source) {
        try {
            long id;
            return source != null &&
                    !source.isEmpty() &&
                    (id = Long.parseLong(source)) > 0 ?
                    id :
                    -1L;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return -1L;
    }

    public static boolean stringToBool(String source) {
        try {
            if (source != null && !source.isEmpty()) {
                return Integer.parseInt(source) == 1;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    public static <T> T getById(JpaRepository<T, Long> repository, Long id) {
        return id == null ||
                id < 1 ?
                null :
                repository.getOne(id);
    }

    public static double stringToDouble(String source) {
        try {
            if (source == null || source.isEmpty()) return 0.0d;
            else {
                return Double.parseDouble(source);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0.0d;
    }

    public static Timestamp stringToTimeStamp(String source) {
        try {
            if (source != null && !source.isEmpty()) {
                return Timestamp.valueOf(source);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public interface Finder<T, P> {
        List<T> execute(P param);
    }

    public interface FinderNoParam<T> {
        List<T> execute();
    }

    public interface Getter<T, P> {
        T execute(P param);
    }
}
