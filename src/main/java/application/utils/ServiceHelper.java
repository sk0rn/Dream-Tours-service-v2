package application.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;

public final class ServiceHelper {
    private ServiceHelper() {

    }

    public static <T> boolean delete(JpaRepository<T, Long> repository, Integer id) {
        if (id != null &&
                id > 0 &&
                repository.existsById((Long) (long) (int) id)) {
            repository.deleteById((Long) (long) (int) id);
            return !repository.existsById((Long) (long) (int) id);
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

    public static <T> T getById(JpaRepository<T, Long> repository, Integer id) {
        return id == null ||
                id < 1 ?
                null :
                repository.getOne((Long) (long) (int) id);
    }

    public static <T> T getById(getter<T, Integer> getter, Integer id) {
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
}
