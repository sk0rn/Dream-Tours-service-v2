package application.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the duration database table.
 * 
 */
@Entity
@Table(name="duration")
@NamedQuery(name="Duration.findAll", query="SELECT d FROM Duration d")
public class Duration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=250)
	private String name;

	@Column(name="number_days", nullable=false)
	private Integer numberDays;

	//bi-directional many-to-one association to TourRelease
	@OneToMany(mappedBy="duration", fetch=FetchType.EAGER)
	private Set<TourRelease> tourReleases;

	public Duration() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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