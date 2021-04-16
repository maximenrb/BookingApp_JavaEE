package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String type;
	
	@ManyToOne
	private Accommodation accommodation;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "room")
	private List<Amenity> amenities;
	
	public Room() {}
	
	public Room(Accommodation accommodation, String type) {
		this.accommodation = accommodation;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

}
