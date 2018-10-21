package application.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the duration database table.
 * 
 */
@Entity
@Table(name="duration")
public class Duration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "durationSeq", sequenceName = "duration_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "durationSeq")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=250)
	private String name;

	@Column(name="number_days", nullable=false)
	private Integer numberDays;

	//bi-directional many-to-one association to TourRelease
	@OneToMany(mappedBy = "duration", fetch = FetchType.LAZY)
	private Set<TourRelease> tourReleases;

	public Duration() {
	}

    public Duration(Integer numberDays, String name) {
        this.name = name;
        this.numberDays = numberDays;
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberDays() {
		return this.numberDays;
	}

	public void setNumberDays(Integer numberDays) {
		this.numberDays = numberDays;
	}

	public Set<TourRelease> getTourReleases() {
		return this.tourReleases;
	}

	public void setTourReleases(Set<TourRelease> tourReleases) {
		this.tourReleases = tourReleases;
	}

	public TourRelease addTourReleas(TourRelease tourReleas) {
		getTourReleases().add(tourReleas);
		tourReleas.setDuration(this);

		return tourReleas;
	}

	public TourRelease removeTourReleas(TourRelease tourReleas) {
		getTourReleases().remove(tourReleas);
		tourReleas.setDuration(null);

		return tourReleas;
	}

}