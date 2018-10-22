package application.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the contact database table.
 * 
 */
@Entity
@Table(name="contact")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
			generator = "contact_id_seq")
	@SequenceGenerator(
			name="contact_id_seq",
			sequenceName="contact_id_seq",
			allocationSize=1
	)
	@Column(unique=true, nullable=false)
    private Long id;

	@Column(nullable=false, length=300)
	private String value;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="client_id", nullable=false)
	private User user;

	public Contact() {
	}

	public Contact(String value) {
		this.value = value;
	}

	public Long getId() {
		return this.id;
	}

    public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}