package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "accommodation")
public class Accommodation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	private User user;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name = "capacity", nullable = false)
	private int capacity;
	
	@Column(name = "numberofrooms", nullable = false)
	private int numberOfRooms;
	
	@Column(columnDefinition = "TEXT", name = "description", nullable = false)
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private HouseRules houseRules;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@OrderColumn
	private List<Picture> pictures = new ArrayList<Picture>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "accommodation")
	private List<Offer> offers;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "accommodation")
	private List<Room> rooms;
	
	public Accommodation() {}

	public Accommodation(User user, String name, Address address, HouseRules houseRules, String type, int capacity, int numberOfRooms, String description) {
		this.user = user;
		this.name = name;
		this.address = address;
		this.houseRules = houseRules;
		this.type = type;
		this.capacity = capacity;
		this.numberOfRooms = numberOfRooms;
		this.description = description;
	}
	
	public void update(String name, String type, int capacity, int numberOfRooms, String description) {
		this.name = name;
		this.type = type;
		this.capacity = capacity;
		this.numberOfRooms = numberOfRooms;
		this.description = description;
	}
	
	public void updateAddress(String streetAndNumber, String complement, String postalCode, String city, String country) {
		this.address.update(streetAndNumber, complement, postalCode, city, country);
	}
	
	public void updateHouseRules(LocalTime arrivalHour, LocalTime departureHour, boolean petAllowed, boolean partyAllowed, boolean smokeAllowed) {
		this.houseRules.update(arrivalHour, departureHour, petAllowed, partyAllowed, smokeAllowed);
	}
	
	public void addPicture(Picture picture) {
		pictures.add(picture);
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public HouseRules getHouseRules() {
		return houseRules;
	}

	public void setHouseRules(HouseRules houseRules) {
		this.houseRules = houseRules;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
