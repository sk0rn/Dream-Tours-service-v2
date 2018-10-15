package domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	private double coast;

	@Column(nullable=false)
	private Integer status;

	//bi-directional many-to-one association to TourRelease
	@ManyToOne
	@JoinColumn(name="tour_release_id", nullable=false)
	private TourRelease tourRelease;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
	private Set<Participant> participants;

	public Order() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getCoast() {
		return this.coast;
	}

	public void setCoast(double coast) {
		this.coast = coast;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public TourRelease getTourRelease() {
		return this.tourRelease;
	}

	public void setTourRelease(TourRelease tourRelease) {
		this.tourRelease = tourRelease;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Participant> getParticipants() {
		return this.participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public Participant addParticipant(Participant participant) {
		getParticipants().add(participant);
		participant.setOrder(this);

		return participant;
	}

	public Participant removeParticipant(Participant participant) {
		getParticipants().remove(participant);
		participant.setOrder(null);

		return participant;
	}

}