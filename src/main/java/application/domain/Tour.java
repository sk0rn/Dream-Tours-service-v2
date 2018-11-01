package application.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * The persistent class for the tour database table.
 */
@Entity
@Table(name = "tour")
public class Tour implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "tourSeq", sequenceName = "tour_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tourSeq")
    @Column(unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name="album_guid_id")
    private Album albumGuid;

    @Column(length = 2147483647)
    private String descr;

    @Column(nullable = false, length = 250)
    private String name;

    @Column(name = "youtube_url", length = 50)
    private String youtubeUrl;

    //bi-directional many-to-many association to Place
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tour_place"
            , joinColumns = {
            @JoinColumn(name = "tour_id", nullable = false)
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "place_id", nullable = false)
    }
    )
    private Set<Place> places;

    //bi-directional many-to-one association to Release
    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    private Set<Release> releases;

    //bi-directional many-to-many association to Subject
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tour_subject",
            joinColumns = @JoinColumn(name = "tour_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "subject_id", nullable = false)
    )
    private Set<Subject> subjects;

    //bi-directional many-to-many association to User
    @ManyToMany(fetch = FetchType.LAZY) //mappedBy = "tours",
    @JoinTable(name = "wishlist",
            joinColumns = @JoinColumn(name = "tour_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "client_id", nullable = false)
    )
    private Set<User> users;

    public Tour() {
    }

    public Tour(Long id, String name, String descr, String youtubeUrl, Album albumGuid) {
        this.id = id;
        this.descr = descr;
        this.name = name;
        this.youtubeUrl = youtubeUrl;
        this.albumGuid = albumGuid;
    }

    public Tour(String name, String descr, String youtubeUrl, Album albumGuid) {
        this.albumGuid = albumGuid;
        this.descr = descr;
        this.name = name;
        this.youtubeUrl = youtubeUrl;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Album getAlbumGuid() {
        return albumGuid;
    }

    public void setAlbumGuid(Album albumGuid) {
        this.albumGuid = albumGuid;
    }

    public String getDescr() {
        return this.descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYoutubeUrl() {
        return this.youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public Set<Place> getPlaces() {
        return this.places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    public Set<Release> getReleases() {
        return this.releases;
    }

    public void setReleases(Set<Release> releases) {
        this.releases = releases;
    }

    public Release addTourReleas(Release tourReleas) {
        getReleases().add(tourReleas);
        tourReleas.setTour(this);

        return tourReleas;
    }

    public Release removeTourReleas(Release tourReleas) {
        getReleases().remove(tourReleas);
        tourReleas.setTour(null);

        return tourReleas;
    }

    public Set<Subject> getSubjects() {
        return this.subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}