package application.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the participant database table.
 * 
 */
@Entity
@Table(name="participant")
public class Participant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="clipping_age")
	private Integer clippingAge;

	@Column(nullable=false)
	private Integer quantity;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="order_id", nullable=false)
	private Order order;

	public Participant() {
		//Because ...
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

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}