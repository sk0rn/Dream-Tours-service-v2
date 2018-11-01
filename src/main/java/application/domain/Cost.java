package application.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the cost database table.
 * 
 */
@Entity
@Table(name="cost")
public class Cost implements Serializable {
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

	//bi-directional many-to-one association to Release
	@ManyToOne
	@JoinColumn(name="tour_release_id", nullable=false)
	private Release release;

	public Cost() {
	}

	public Cost(Release release, Boolean kind, double cost, Integer clippingAge, Boolean isParticipant) {
		this.clippingAge = clippingAge;
		this.cost = cost;
		this.isParticipant = isParticipant;
		this.kind = kind;
		this.release = release;
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

	public Release getRelease() {
		return this.release;
	}

	public void setRelease(Release release) {
		this.release = release;
	}

}