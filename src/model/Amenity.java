package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "amenity")
public class Amenity {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	private Room room;
	
	public Amenity() {}
	
	public Amenity(Room room, String name) {
		this.room = room;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
