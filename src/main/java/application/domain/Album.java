package application.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "album")
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "album_id_seq")
    @SequenceGenerator(
            name="album_id_seq",
            sequenceName="album_id_seq",
            allocationSize=1
    )
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(nullable=false, length=60)
    private String name;

    public Album() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
