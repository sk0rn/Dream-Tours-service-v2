package application.repository;

import application.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "select exists(select id from contact where value = :value limit 1)", nativeQuery = true)
    Boolean existsByValue(@Param("value") String value);

}
