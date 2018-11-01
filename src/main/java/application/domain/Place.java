package application.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the place database table.
 * 
 */
@Entity
@Table(name="place")
public class Place implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "placeSeq", sequenceName = "place_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "placeSeq")
	@Column(unique=true, nullable=false)
	private Long id;

    @Column(columnDefinition = "text")
    private String descr;

	@Column(nullable=false, length=250)
	private String name;

	//bi-directional many-to-many association to Tour
	@ManyToMany(mappedBy = "places", fetch = FetchType.LAZY)
	private Set<Tour> tours;

	public Place() {
	}

    public Place(String name, String descr) {
        this.name = name;
        this.descr = descr;
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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