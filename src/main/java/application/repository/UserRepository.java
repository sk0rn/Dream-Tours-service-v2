package application.repository;

import application.domain.User;
import application.service.subsets.IdOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByLogin(String login);

    @Query(value = "select pass from users where login = :login", nativeQuery = true)
    String findUserPassByLogin(@Param("login") String login);

    @Query(value = "select options from users where login = :login", nativeQuery = true)
    Integer findUserRoleByLogin(@Param("login") String login);

    @Query(value = "select id from users where login = :login", nativeQuery = true)
    Integer findUserIdByLogin(@Param("login") String login);

    @Query(value = "select tour_id as id from wishlist where client_id = :user_id", nativeQuery = true)
    Set<IdOnly> getWishList(@Param("user_id") long userId);
}
