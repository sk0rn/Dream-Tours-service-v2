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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "album_guid", nullable = false, length = 60)
    private String albumGuid;

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

    //bi-directional many-to-one association to TourRelease
    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    private Set<TourRelease> tourReleases;

    //bi-directional many-to-many association to Subject
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tour_subject"
            , joinColumns = {
            @JoinColumn(name = "tour_id", nullable = false)
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "subject_id", nullable = false)
    }
    )
    private Set<Subject> subjects;

    //bi-directional many-to-many association to User
    @ManyToMany(mappedBy = "tours", fetch = FetchType.LAZY)
    private Set<User> users;

    public Tour() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumGuid() {
        return this.albumGuid;
    }

    public void setAlbumGuid(String albumGuid) {
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

    public Set<TourRelease> getTourReleases() {
        return this.tourReleases;
    }

    public void setTourReleases(Set<TourRelease> tourReleases) {
        this.tourReleases = tourReleases;
    }

    public TourRelease addTourReleas(TourRelease tourReleas) {
        getTourReleases().add(tourReleas);
        tourReleas.setTour(this);

        return tourReleas;
    }

    public TourRelease removeTourReleas(TourRelease tourReleas) {
        getTourReleases().remove(tourReleas);
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