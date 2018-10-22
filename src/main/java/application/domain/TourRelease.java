package application.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tour_release database table.
 * 
 */
@Entity
@Table(name="tour_release")
public class TourRelease implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "releaseSeq", sequenceName = "tour_release_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "releaseSeq")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="begin_time", nullable=false)
	private Timestamp beginTime;

	@Column(nullable=false)
	private Integer capacity;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy = "tourRelease", fetch = FetchType.LAZY)
	private Set<Order> orders;

	//bi-directional many-to-one association to TourCost
	@OneToMany(mappedBy = "tourRelease", fetch = FetchType.LAZY)
	private Set<TourCost> tourCosts;

	//bi-directional many-to-one association to Duration
	@ManyToOne
	@JoinColumn(name="duration_id", nullable=false)
	private Duration duration;

	//bi-directional many-to-one association to Tour
	@ManyToOne
	@JoinColumn(name="tour_id", nullable=false)
	private Tour tour;

	public TourRelease() {
	}

    public TourRelease(Tour tour, Duration duration, Timestamp beginTime, Integer capacity) {
        this.beginTime = beginTime;
        this.capacity = capacity;
        this.duration = duration;
        this.tour = tour;
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setTourRelease(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setTourRelease(null);

		return order;
	}

	public Set<TourCost> getTourCosts() {
		return this.tourCosts;
	}

	public void setTourCosts(Set<TourCost> tourCosts) {
		this.tourCosts = tourCosts;
	}

	public TourCost addTourCost(TourCost tourCost) {
		getTourCosts().add(tourCost);
		tourCost.setTourRelease(this);

		return tourCost;
	}

	public TourCost removeTourCost(TourCost tourCost) {
		getTourCosts().remove(tourCost);
		tourCost.setTourRelease(null);

		return tourCost;
	}

	public Duration getDuration() {
		return this.duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Tour getTour() {
		return this.tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

}