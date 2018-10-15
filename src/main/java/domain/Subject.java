package domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the subject database table.
 * 
 */
@Entity
@Table(name="subject")
@NamedQuery(name="Subject.findAll", query="SELECT s FROM Subject s")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=2147483647)
	private String descr;

	@Column(nullable=false, length=250)
	private String name;

	//bi-directional many-to-many association to Tour
	@ManyToMany(mappedBy="subjects", fetch=FetchType.EAGER)
	private Set<Tour> tours;

	public Subject() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<Tour> getTours() {
		return this.tours;
	}

	public void setTours(Set<Tour> tours) {
		this.tours = tours;
	}

}