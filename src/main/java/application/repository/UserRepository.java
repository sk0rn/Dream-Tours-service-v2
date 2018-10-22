package application.repository;

import application.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByLogin(String login);

    @Query(value = "select pass from users where login = :login", nativeQuery = true)
    String findUserPassByLogin(@Param("login") String login);

    @Query(value = "select options from users where login = :login", nativeQuery = true)
    Integer findUserRoleByLogin(@Param("login") String login);

    @Query(value = "select id from users where login = :login", nativeQuery = true)
    Integer findUserIdByLogin(@Param("login") String login);
}
