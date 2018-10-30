package application.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "files_id_seq")
    @SequenceGenerator(
            name = "files_id_seq",
            sequenceName = "roles_id_seq",
            allocationSize = 1
    )
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    public File() {
    }

    public File(String name) {
        this.name = name;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        File that = (File) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .toHashCode();
    }
}
