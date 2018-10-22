package application.utils;

import lombok.extern.log4j.Log4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Log4j
public final class ServiceHelper {
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

    public static <T> boolean save(JpaRepository<T, Long> repository, T entity) {
        return entity != null && repository.save(entity) != null;
    }

    public static <T, P> List<T> getListByParam(finder<T, P> finder, P param) {
        List<T> entities;
        return param == null ||
                (entities = finder.execute(param)) == null ?
                Collections.emptyList() :
                entities;
    }

    public static <T, P> T getEntityByParam(getter<T, P> getter, P param) {
        return param == null ? null : getter.execute(param);
    }

    public static <T> T getById(JpaRepository<T, Long> repository, Long id) {
        return id == null ||
                id < 1 ?
                null :
                repository.getOne(id);
    }

    public static <T> T getById(getter<T, Long> getter, Long id) {
        return id == null ||
                id < 1 ?
                null :
                getter.execute(id);
    }

    public interface finder<T, P> {
        List<T> execute(P param);
    }

    public interface getter<T, P> {
        T execute(P param);
    }

    public static Long StringToId(String source) {
        try {
            Long id;
            return source != null &&
                    !source.isEmpty() &&
                    (id = Long.parseLong(source)) != null &&
                    id > 0 ?
                    id :
                    -1L;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return -1L;
    }

    public static boolean StringToBool(String source) {
        try {
            if (source != null || !source.isEmpty()) {
                return Integer.parseInt(source) == 1;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    public static double StringToDouble(String source) {
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

    public static Timestamp StringToTimeStamp(String source) {
        try {
            if (source != null && !source.isEmpty()) {
                return Timestamp.valueOf(source);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
