package application.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the tour_cost database table.
 * 
 */
@Entity
@Table(name="tour_cost")
public class TourCost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "costSeq", sequenceName = "tour_cost_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "costSeq")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="clipping_age")
	private Integer clippingAge;

	@Column(nullable=false)
	private double cost;

	@Column(name="is_participant", nullable=false)
	private Boolean isParticipant;

	@Column(nullable=false)
	private Boolean kind;

	//bi-directional many-to-one association to TourRelease
	@ManyToOne
	@JoinColumn(name="tour_release_id", nullable=false)
	private TourRelease tourRelease;

	public TourCost() {
	}

	public TourCost(TourRelease tourRelease, Boolean kind, double cost, Integer clippingAge, Boolean isParticipant) {
		this.clippingAge = clippingAge;
		this.cost = cost;
		this.isParticipant = isParticipant;
		this.kind = kind;
		this.tourRelease = tourRelease;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getClippingAge() {
		return this.clippingAge;
	}

	public void setClippingAge(Integer clippingAge) {
		this.clippingAge = clippingAge;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Boolean getIsParticipant() {
		return this.isParticipant;
	}

	public void setIsParticipant(Boolean isParticipant) {
		this.isParticipant = isParticipant;
	}

	public Boolean getKind() {
		return this.kind;
	}

	public void setKind(Boolean kind) {
		this.kind = kind;
	}

	public TourRelease getTourRelease() {
		return this.tourRelease;
	}

	public void setTourRelease(TourRelease tourRelease) {
		this.tourRelease = tourRelease;
	}

}